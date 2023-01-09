package com.jogamais.ufcg.utils.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CourtError {

    static final String COURT_NOT_EXIST = "Quadra(s) não cadastrada(s)";
    static final String UNAVAILABLE_APPOINTMENT = "O horário: não está disponível.";

    public static ResponseEntity<CustomTypeError> errorUnavailableAppointment() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(CourtError.UNAVAILABLE_APPOINTMENT),
                HttpStatus.CONFLICT);
    }

    public static ResponseEntity<CustomTypeError> errorCourtNotExist() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(CourtError.COURT_NOT_EXIST),
                HttpStatus.NOT_FOUND);
    }
}
