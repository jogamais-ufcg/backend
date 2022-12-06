package com.jogamais.ufcg.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class CourtError {

    static final String UNAVAILABLE_APPOINTMENT = "O horário: não está disponível.";

    public static ResponseEntity<CustomTypeError> errorUnavailableAppointment() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(CourtError.UNAVAILABLE_APPOINTMENT), HttpStatus.CONFLICT);
    }
}
