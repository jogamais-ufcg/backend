package com.jogamais.ufcg.repositories;

import com.jogamais.ufcg.models.AdminAppointment;
import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.models.pk.AppointmentPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminAppointmentRepository extends JpaRepository<AdminAppointment, AppointmentPK> {

    Optional<AdminAppointment> findById_UserAndId_Court(User user, Court court);

}
