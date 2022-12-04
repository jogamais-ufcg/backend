package com.jogamais.ufcg.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserError {

    static final String USER_NOT_EXIST = "O usuário não cadastrado.";

    static final String USER_ALREADY_EXIST = "O usuário %s já está cadastrado com o CPF: %s";

    public static ResponseEntity<CustomTypeError> errorUserNotExist() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(UserError.USER_NOT_EXIST), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomTypeError> errorUserAlreadyExist(String cpf) {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(String.format(UserError.USER_ALREADY_EXIST, cpf)), HttpStatus.CONFLICT);
    }
}
