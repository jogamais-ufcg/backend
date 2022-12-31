package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.AdminAppointment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AdminAppointmentDTO {
    private String userName;
    private String userEmail;
    private String userPhone;
    private Date appointmentDate;
    private String playerList;

    public AdminAppointment getModel() {
        return new AdminAppointment(
                null,
                this.userName,
                this.userEmail,
                this.userPhone,
                this.appointmentDate,
                this.playerList);
    }
}
