package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.UserAppointment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserAppointmentDTO {
    private Date startAppointmentDate;
    private String playerList;

    public UserAppointment getModel() {
        return new UserAppointment(
                null,
                this.startAppointmentDate,
                null,
                this.playerList);
    }
}
