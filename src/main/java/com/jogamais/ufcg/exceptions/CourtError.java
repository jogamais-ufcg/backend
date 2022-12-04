package com.jogamais.ufcg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class CourtError {

    static final String UNAVAILABLE_APPOINTMENT = "O horário: %s não está disponível.";

    public static ResponseEntity<CustomTypeError> errorUnavailableAppointment(Date date) {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(String.format(CourtError.UNAVAILABLE_APPOINTMENT,
                date)), HttpStatus.CONFLICT);
    }
}
