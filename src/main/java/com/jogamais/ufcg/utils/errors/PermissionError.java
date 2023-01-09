package com.jogamais.ufcg.utils.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PermissionError {

    static final String PERMISSION_ALREADY_EXIST = "Essa permissão já está cadastrada, verifique se escrita está correta!";

    static final String PERMISSION_NOT_EXIST = "Permissão não cadastrada!";

    public static ResponseEntity<CustomTypeError> errorPermissionAlreadyExist() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(PermissionError.PERMISSION_ALREADY_EXIST),
                HttpStatus.CONFLICT);
    }

    public static ResponseEntity<CustomTypeError> errorPermissionNotExist() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(PermissionError.PERMISSION_NOT_EXIST),
                HttpStatus.NOT_FOUND);
    }
}
