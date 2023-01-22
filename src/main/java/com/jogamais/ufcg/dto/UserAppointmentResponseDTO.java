package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.UserAppointment;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserAppointmentResponseDTO {

    private final Date startAppointmentDate;

    private final Date endAppointmentDate;
    private final String playerList;

    public UserAppointmentResponseDTO(UserAppointment userAppointment) {
        this.startAppointmentDate = userAppointment.getStartAppointmentDate();
        this.endAppointmentDate = userAppointment.getEndAppointmentDate();
        this.playerList = userAppointment.getPlayerList();
    }
}
