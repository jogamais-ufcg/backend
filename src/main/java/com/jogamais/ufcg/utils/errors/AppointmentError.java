package com.jogamais.ufcg.utils.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AppointmentError {

    static final String APPOINTMENT_NOT_EXIST = "Agendamento não encontrado.";

    static final String APPOINTMENTS_NOT_EXIST = "Nenhum agendamento encontrado.";

    static final String APPOINTMENT_ALREADY_EXIST = "Este agendamento já foi criado.";

    static final String APPOINTMENT_USER_OR_COURT_NULL = "Usuário ou quadra inexistente.";

    static final String TIME_UNAVAILABLE = "Horário de agendamento indisponível.";

    static final String COURT_UNAVAILABLE = "Esta quadra não possui agendamentos nesse dia!";

    static final String CANCELLATION_NOT_ALLOWED = "Cancelar agendamento com menos de 2 dias de antecedência não é permitido!";

    static final String INVALID_DATE = "Data de agendamento inválida! Confira o dia e o horário novamente.";

    public static ResponseEntity<CustomTypeError> errorAppointmentNotExist() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AppointmentError.APPOINTMENT_NOT_EXIST),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomTypeError> errorAppointmentInvalidDate() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AppointmentError.INVALID_DATE),
                HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomTypeError> errorAppointmentUserOrCourt() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AppointmentError.APPOINTMENT_USER_OR_COURT_NULL),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomTypeError> errorAppointmentTimeUnavailable() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AppointmentError.TIME_UNAVAILABLE),
                HttpStatus.CONFLICT);
    }

    public static ResponseEntity<CustomTypeError> errorAppointmentCourtUnavailable() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AppointmentError.COURT_UNAVAILABLE),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomTypeError> errorAppointmentCancellationNotAllowed() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AppointmentError.CANCELLATION_NOT_ALLOWED),
                HttpStatus.FORBIDDEN);
    }

    public static ResponseEntity<CustomTypeError> errorAppointmentsNotExist() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AppointmentError.APPOINTMENTS_NOT_EXIST),
                HttpStatus.NOT_FOUND);
    }
}
