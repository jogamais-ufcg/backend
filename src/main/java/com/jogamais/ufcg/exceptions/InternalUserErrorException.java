package com.jogamais.ufcg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class InternalUserErrorException extends Throwable {

    static final String USER_NOT_EXIST = "O usuário não cadastrado.";

    static final String USER_ALREADY_EXIST = "O usuário %s já está cadastrado com o CPF: %s";

    public static ResponseEntity<CustomTypeErrorException> errorUserNotExist() {
        return new ResponseEntity<CustomTypeErrorException>(new CustomTypeErrorException(InternalUserErrorException.USER_NOT_EXIST), HttpStatus.NOT_FOUND);
    }

    //é necessário que tenha o model de cliente
    public static ResponseEntity<CustomTypeErrorException> errorUserAlreadyExist(String cpf) {
        return new ResponseEntity<CustomTypeErrorException>(new CustomTypeErrorException(String.format(InternalUserErrorException.USER_ALREADY_EXIST, cpf)), HttpStatus.CONFLICT);
    }
}
