package com.jogamais.ufcg.utils.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AppointmentError {

    static final String APPOINTMENT_NOT_EXIST = "Agendamento não encontrado.";

    static final String APPOINTMENTS_NOT_EXIST = "Nenhum agendamento encontrado.";

    static final String APPOINTMENT_ALREADY_EXIST = "Este agendamento já foi criado.";

    static final String APPOINTMENT_USER_OR_COURT_NULL = "Usuário ou quadra inexistente.";

    static final String APPOINTMENT_USER_ALREADY_EXIST = "Este usuário já possui um agendamento.";

    static final String TIME_UNAVAILABLE = "Horário de agendamento indisponível.";

    public static ResponseEntity<CustomTypeError> errorAppointmentNotExist() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AppointmentError.APPOINTMENT_NOT_EXIST),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomTypeError> errorAppointmentUserOrCourt() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AppointmentError.APPOINTMENT_USER_OR_COURT_NULL),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomTypeError> errorAppointmentTimeUnavailable() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AppointmentError.TIME_UNAVAILABLE),
                HttpStatus.CONFLICT);
    }
}
