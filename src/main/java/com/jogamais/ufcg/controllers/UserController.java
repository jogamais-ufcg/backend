package com.jogamais.ufcg.controllers;

import com.jogamais.ufcg.dto.UserDTO;
import com.jogamais.ufcg.dto.UserEditDTO;
import com.jogamais.ufcg.dto.UserResponseDTO;
import com.jogamais.ufcg.exceptions.UserException;
import com.jogamais.ufcg.exceptions.UserInvalidInputException;
import com.jogamais.ufcg.exceptions.UserInvalidNumberException;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.services.UserService;
import com.jogamais.ufcg.utils.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
        Page<User> users = userService.findAll(PageRequest.of(page, 10));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@RequestPart String name, @RequestPart String cpf, @RequestPart String email, @RequestPart String phoneNumber, @RequestPart String password, @RequestPart String isUFCGMember, @RequestPart String isStudent, @RequestPart MultipartFile fileFront, @RequestPart MultipartFile fileBack) {
        UserDTO userDTO = new UserDTO(name, cpf, email, phoneNumber, password, isUFCGMember.equals("true"), isStudent.equals("true"));

        User createdUser = userService.create(userDTO.getModel());

        UserResponseDTO response = new UserResponseDTO(createdUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody UserEditDTO userEditDTO) throws UserException, IOException {
        try {
            userService.editUser(id, userEditDTO);
            return new ResponseEntity<>("Usuário editado com sucesso!", HttpStatus.OK);
        } catch (UserException e) {
            return UserError.errorUserNotExist();
        } catch (UserInvalidNumberException e) {
            return UserError.errorInvalidNumber();
        } catch (UserInvalidInputException e) {
            return UserError.errorInvalidInput();
        }
    }
}
