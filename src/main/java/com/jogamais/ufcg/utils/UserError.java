package com.jogamais.ufcg.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserError {

    static final String USER_NOT_EXIST = "Usuário(s) não cadastrado(s)";

    static final String USER_ALREADY_EXIST = "Esse usuário já está cadastrado, verifique o seu email, cpf ou matrícula";

    static final String INVALID_NUMBER = "Número inválido, tente novamente!";

    static final String INVALID_INPUT = "Por favor, preencha com um nome válido!";

    static final String MISSING_ENROLLMENT = "Um aluno da UFCG precisa possuir uma matrícula!";

    static final String MISSING_FILE_BACK = "O verso do documento é obrigatório!";
    private static final String INVALID_CPF = "O número de CPF informado é inválido!";
    private static final String INVALID_ENROLLMENT = "O número de matrícula informado é inválido!";

    public static ResponseEntity<CustomTypeError> errorUserNotExist() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(UserError.USER_NOT_EXIST),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomTypeError> errorUserAlreadyExist(String cpf) {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(UserError.USER_ALREADY_EXIST),
                HttpStatus.CONFLICT);
    }

    public static ResponseEntity<CustomTypeError> errorInvalidNumber() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(UserError.INVALID_NUMBER),
                HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomTypeError> errorInvalidInput() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(UserError.INVALID_INPUT),
                HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomTypeError> errorMissingEnrollment() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(UserError.MISSING_ENROLLMENT),
                HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomTypeError> errorMissingFileBack() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(UserError.MISSING_FILE_BACK),
                HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomTypeError> errorInvalidCPF() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(UserError.INVALID_CPF),
                HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomTypeError> errorInvalidEnrollment() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(UserError.INVALID_ENROLLMENT),
                HttpStatus.BAD_REQUEST);
    }
}
