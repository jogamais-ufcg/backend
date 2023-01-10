package com.jogamais.ufcg.utils.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CourtError {

    static final String COURT_NOT_EXIST = "Quadra(s) não cadastrada(s).";

    static final String COURT_ALREADY_EXIST = "Essa quadra já está cadastrada no sistema, verifique se o nome está correto.";
    static final String UNAVAILABLE_APPOINTMENT = "O horário: não está disponível.";

    static final String INVALID_OPENING_HOURS = "O horário de fechamento precisa ser posterior ao horário de abertura, " +
                                                "verifique se os horários estão escritos corretamente.";

    static final String INVALID_APPOINTMENT_DURATION = "A duração do agendamento precisa ser maior que 1 hora e " +
                                                       "os intervalos precisam ser entre 30 minutos(ex: 1h30min, 2h, 2h30min).";

    static final String INVALID_RECURRENCE_INTERVAL_PERIOD = "O intervalo de recorrência precisa ser superior à 7 dias.";

    public static ResponseEntity<CustomTypeError> errorUnavailableAppointment() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(CourtError.UNAVAILABLE_APPOINTMENT),
                HttpStatus.CONFLICT);
    }

    public static ResponseEntity<CustomTypeError> errorCourtNotExist() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(CourtError.COURT_NOT_EXIST),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomTypeError> errorCourtAlreadyExist() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(CourtError.COURT_ALREADY_EXIST),
                HttpStatus.CONFLICT);
    }

    public static ResponseEntity<CustomTypeError> errorCourtInvalidOpeningHours() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(CourtError.INVALID_OPENING_HOURS),
                HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomTypeError> errorCourtInvalidAppointmentDuration() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(CourtError.INVALID_APPOINTMENT_DURATION),
                HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomTypeError> errorCourtInvalidRecurrenceIntervalPeriod() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(CourtError.INVALID_RECURRENCE_INTERVAL_PERIOD),
                HttpStatus.BAD_REQUEST);
    }

}
