package com.jogamais.ufcg.exceptions;

import com.jogamais.ufcg.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class InternalUserError {

    static final String USER_NOT_EXIST = "O usuário não cadastrado.";

    static final String USER_ALREADY_EXIST = "O usuário %s já está cadastrado com o CPF: %s";

    public static ResponseEntity<CustomTypeError> errorUserNotExist() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(InternalUserError.USER_NOT_EXIST), HttpStatus.NOT_FOUND);
    }

    //é necessário que tenha o model de cliente
    public static ResponseEntity<CustomTypeError> errorUserAlreadyExist(String cpf) {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(String.format(InternalUserError.USER_ALREADY_EXIST, cpf)), HttpStatus.CONFLICT);
    }
}
