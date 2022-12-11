package com.jogamais.ufcg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

    public InvalidJwtAuthenticationException(String msg) {
        super(msg);
    }

    static final String INVALID_JWT_AUTHENTICATION = "Token JWT Inválido.";

    public static ResponseEntity<CustomTypeErrorException> errorInvalidJwtAuthentication() {
        return new ResponseEntity<CustomTypeErrorException>(new CustomTypeErrorException(InvalidJwtAuthenticationException.INVALID_JWT_AUTHENTICATION), HttpStatus.FORBIDDEN);
    }
}
