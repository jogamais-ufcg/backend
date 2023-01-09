package com.jogamais.ufcg.repositories;

import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.models.UserAppointment;
import com.jogamais.ufcg.models.pk.AppointmentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserAppointmentRepository extends JpaRepository<UserAppointment, AppointmentPK> {

    Optional<UserAppointment> findById_UserAndId_Court(User user, Court court);

    List<UserAppointment> findByStartAppointmentDateAndId_Court(Date startAppointmentDate, Court court);
}
