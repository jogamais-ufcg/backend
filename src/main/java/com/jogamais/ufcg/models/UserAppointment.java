package com.jogamais.ufcg.models;

import java.util.Date;

import com.jogamais.ufcg.models.pk.AppointmentPK;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_APPOINTMENT")
public class UserAppointment {

    @EmbeddedId
    private AppointmentPK id;

    @Column(name = "APPOINTMENT_DATE", nullable = false)
    private Date appointmentDate;

    @Column(name = "PLAYER_LIST", nullable = false)
    private String playerList;
}
