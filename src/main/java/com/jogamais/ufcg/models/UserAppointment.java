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

    public void setAppointmentInterval(Date startDate, int durationInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        this.startAppointmentDate = calendar.getTime();
        calendar.add(Calendar.MINUTE, durationInMinutes);
        this.endAppointmentDate = calendar.getTime();
    }
}
