package com.jogamais.ufcg.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AppointmentError {

    static final String APPOINTMENT_NOT_EXIST = "Agendamento não encontrado.";

    static final String APPOINTMENTS_NOT_EXIST = "Nenhum agendamento encontrado.";

    static final String APPOINTMENT_ALREADY_EXIST = "Este agendamento já existe.";

    public static ResponseEntity<CustomTypeError> errorAppointmentNotExist() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AppointmentError.APPOINTMENT_NOT_EXIST),
                HttpStatus.NOT_FOUND);
    }
}
