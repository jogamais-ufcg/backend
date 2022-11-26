package com.jogamais.ufcg.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroUsuarioInterno {

    static final String USER_NOT_EXIST = "O usuário não cadastrado.";

    static final String USER_ALREADY_EXIST = "O usuário %s já está cadastrado com o CPF: %s";

    public static ResponseEntity<CustomErrorType> errorUserNotExist() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroUsuarioInterno.USER_NOT_EXIST), HttpStatus.NOT_FOUND);
    }

    //é necessário que tenha o model de cliente
    public static ResponseEntity<CustomErrorType> errorUserAlreadyExist(String name, String cpf) {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroUsuarioInterno.USER_ALREADY_EXIST, User.getName(), User.getCpf()), HttpStatus.CONFLICT);
    }
}
