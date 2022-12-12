package com.jogamais.ufcg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jogamais.ufcg.vo.AccountCredentialsVO;
import com.jogamais.ufcg.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
        if (authService.checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário ou Senha inválidos!");
        var token = authService.signin(data);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário ou Senha Inválidos!");
        return token;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/refresh/{username}", method = RequestMethod.PUT)
    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken) {
        if (authService.checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário ou Senha inválidos!");
        var token = authService.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário ou Senha inválidos!");
        return token;
    }

}
