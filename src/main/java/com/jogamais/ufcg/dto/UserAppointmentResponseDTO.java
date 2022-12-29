package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.UserAppointment;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserAppointmentResponseDTO {

    private final Date appointmentDate;
    private final String playerList;

    public UserAppointmentResponseDTO(UserAppointment userAppointment) {
        this.appointmentDate = userAppointment.getAppointmentDate();
        this.playerList = userAppointment.getPlayerList();
    }
}
