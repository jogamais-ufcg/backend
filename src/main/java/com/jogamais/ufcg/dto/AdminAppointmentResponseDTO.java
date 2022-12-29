package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.AdminAppointment;
import lombok.Getter;

import java.util.Date;

@Getter
public class AdminAppointmentResponseDTO {

    private final String userName;
    private final String userEmail;
    private final String userPhone;
    private final Date appointmentDate;
    private final String playerList;

    public AdminAppointmentResponseDTO(AdminAppointment adminAppointment) {
        this.userName = adminAppointment.getUserName();
        this.userEmail = adminAppointment.getUserEmail();
        this.userPhone = adminAppointment.getUserPhone();
        this.appointmentDate = adminAppointment.getAppointmentDate();
        this.playerList = adminAppointment.getPlayerList();
    }


}
