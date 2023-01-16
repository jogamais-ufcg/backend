package com.jogamais.ufcg.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jogamais.ufcg.dto.*;
import com.jogamais.ufcg.exceptions.*;
import com.jogamais.ufcg.models.Permission;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.services.UserService;
import com.jogamais.ufcg.utils.errors.PermissionError;
import com.jogamais.ufcg.utils.errors.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController implements IController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            User user = userService.getById(id);
            return new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.OK);
        } catch (UserException e) {
            return UserError.errorUserNotExist();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>("Usuário com ID: " + id + " removido com sucesso!", HttpStatus.OK);
        } catch (UserException e) {
            return UserError.errorUserNotExist();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(int page) {
        Page<User> usersList = userService.findAll(PageRequest.of(page, 10));
        List<UserResponseDTO> response = usersList.stream().map(UserResponseDTO::new).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@RequestPart String name, @RequestPart String cpf, @RequestPart String email,
            @RequestPart(required = false) String enrollment, @RequestPart String phoneNumber,
            @RequestPart String password, @RequestPart String isUFCGMember, @RequestPart String isStudent,
            @RequestPart MultipartFile fileFront, @RequestPart(required = false) MultipartFile fileBack) {
        UserCreateDTO userDTO = new UserCreateDTO(name, cpf, enrollment, email, phoneNumber, password,
                isUFCGMember.equals("true"), isStudent.equals("true"));

        User createdUser;
        try {
            createdUser = userService.createWithFiles(userDTO.getModel(), fileFront, fileBack);
        } catch (UserException e) {
            return UserError.errorUserAlreadyExist(userDTO.getCpf());
        } catch (UserMissingEnrollmentException e) {
            return UserError.errorMissingEnrollment();
        } catch (UserMissingFileBack e) {
            return UserError.errorMissingFileBack();
        } catch (UserInvalidCPF e) {
            return UserError.errorInvalidCPF();
        } catch (UserInvalidNumberException e) {
            return UserError.errorInvalidNumber();
        } catch (UserInvalidEnrollment e) {
            return UserError.errorInvalidEnrollment();
        }

        UserResponseDTO response = new UserResponseDTO(createdUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public ResponseEntity<?> createPermission(@RequestBody Permission permission) {
        try {
            userService.createPermission(permission);
        } catch (PermissionException e) {
            return PermissionError.errorPermissionAlreadyExist();
        }
        return new ResponseEntity<>("Permissão criada com sucesso!", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userId}/permission/{permissionId}", method = RequestMethod.POST)
    public ResponseEntity<?> addPermissionToUser(@PathVariable Long userId, @PathVariable Long permissionId) {
        try {
            User user = userService.getById(userId);
            Permission permission = userService.getPermissionById(permissionId);
            userService.addPermissionToUser(user.getEmail(), permission.getDescription());
            return new ResponseEntity<>(
                    "Permissão " + permission.getDescription() + "adicionada ao Usuário com ID: " + user.getId() + " com sucesso!"
                    , HttpStatus.OK
            );
        } catch (UserException e) {
            return UserError.errorUserNotExist();
        } catch (PermissionException e) {
            return PermissionError.errorPermissionNotExist();
        }
    }

    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String email = decodedJWT.getSubject();
                User user = userService.getByEmail(email);
                String access_token = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 20 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("permissions", user.getPermissions().stream().map(Permission::getDescription).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh Token não existe!");
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody UserEditDTO userEditDTO)
            throws UserException, IOException {
        try {
            User editedUser = userService.editUser(id, userEditDTO);
            return new ResponseEntity<>(new UserResponseDTO(editedUser), HttpStatus.OK);
        } catch (UserException e) {
            return UserError.errorUserNotExist();
        } catch (UserInvalidNumberException e) {
            return UserError.errorInvalidNumber();
        } catch (UserInvalidInputException e) {
            return UserError.errorInvalidInput();
        }
    }

    @PutMapping("/{id}/confirmations")
    public ResponseEntity<?> userConfirmation(@PathVariable Long id,
            @RequestBody UserConfirmationDTO userConfirmationDTO) {
        try {
            userService.userConfirmation(id, userConfirmationDTO);
            return new ResponseEntity<>("Email de resposta ao cadastro foi enviado ao usuário!", HttpStatus.OK);
        } catch (UserException e) {
            return UserError.errorUserNotExist();
        }
    }

    @RequestMapping(value = "/{id}/blockedUser", method = RequestMethod.PATCH)
    public ResponseEntity<?> blockedUser(@PathVariable Long id) {
        try {
            userService.blockedUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserException e) {
            return UserError.errorUserNotExist();
        }
    }
}
