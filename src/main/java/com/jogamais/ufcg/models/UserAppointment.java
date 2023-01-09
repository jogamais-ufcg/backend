package com.jogamais.ufcg.models;

import java.util.Calendar;
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

    @Column(name = "START_APPOINTMENT_DATE", nullable = false)
    private Date startAppointmentDate;

    @Column(name = "END_APPOINTMENT_DATE", nullable = false)
    private Date endAppointmentDate;

    @Column(name = "PLAYER_LIST", nullable = false)
    private String playerList;

    public void setAppointmentInterval(Date startDate, int durationInHours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        this.startAppointmentDate = calendar.getTime();
        calendar.add(Calendar.HOUR_OF_DAY, durationInHours);
        this.endAppointmentDate = calendar.getTime();
    }
}
