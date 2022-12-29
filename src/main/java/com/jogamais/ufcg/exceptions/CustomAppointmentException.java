package com.jogamais.ufcg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class CustomAppointmentException {

    static final String APPOINTMENT_NOT_EXIST = "Este agendamento não existe.";

    static final String APPOINTMENTS_NOT_EXIST = "Nenhum agendamento encontrado.";

    static final String APPOINTMENT_ALREADY_EXIST = "Este agendamento já existe.";

    public static ResponseEntity<CustomTypeErrorException> errorAppointmentNotExist() {
        return new ResponseEntity<CustomTypeErrorException>(
                new CustomTypeErrorException(CustomAppointmentException.APPOINTMENT_NOT_EXIST), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomTypeErrorException> errorAppointmentsNotExist() {
        return new ResponseEntity<CustomTypeErrorException>(
                new CustomTypeErrorException(CustomAppointmentException.APPOINTMENTS_NOT_EXIST), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomTypeErrorException> errorAppointmentsAlreadyExist() {
        return new ResponseEntity<CustomTypeErrorException>(
                new CustomTypeErrorException(CustomAppointmentException.APPOINTMENT_ALREADY_EXIST), HttpStatus.CONFLICT);
    }
}
