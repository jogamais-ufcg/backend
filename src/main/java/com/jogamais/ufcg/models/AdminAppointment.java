package com.jogamais.ufcg.models;

import com.jogamais.ufcg.models.pk.AppointmentPK;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADMIN_APPOINTMENT")
public class AdminAppointment {

    @EmbeddedId
    private AppointmentPK id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "USER_PHONE", nullable = false)
    private String userPhone;

    @Column(name = "APPOINTMENT_DATE", nullable = false)
    private Date appointmentDate;

    @Column(name = "PLAYER_LIST", nullable = false)
    private String playerList;

}