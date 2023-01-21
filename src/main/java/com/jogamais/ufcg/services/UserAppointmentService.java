package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.AppointmentException;
import com.jogamais.ufcg.exceptions.AppointmentUserOrCourtExcpetion;
import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.models.UserAppointment;
import com.jogamais.ufcg.models.pk.AppointmentPK;
import com.jogamais.ufcg.repositories.UserAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserAppointmentService implements IService<UserAppointment> {

    @Autowired
    private UserAppointmentRepository userRepository;

    public UserAppointment findByUserAndCourt(User user, Court court)
            throws AppointmentException, AppointmentUserOrCourtExcpetion {
        if (user == null || court == null) {
            throw new AppointmentUserOrCourtExcpetion();
        }

        return userRepository.findById_UserAndId_Court(user, court).orElseThrow(AppointmentException::new);
    }

    @Override
    public UserAppointment create(UserAppointment userAppointment) {
        return userRepository.save(userAppointment);
    }

    public void deleteByUserAndCourt(User user, Court court)
            throws AppointmentException, AppointmentUserOrCourtExcpetion {
        UserAppointment userAppointment = findByUserAndCourt(user, court);
        userRepository.delete(userAppointment);
    }

    @Override
    public Page<UserAppointment> search(String searchTerm, int page, int size) {
        return null;
    }

    @Override
    public Page<UserAppointment> findAll(PageRequest page) {
        Page<UserAppointment> userAppointmentList = userRepository.findAll(PageRequest.of(page.getPageNumber(), 10));

        return userAppointmentList;
    }

    public List<UserAppointment> findAppointmentsByDayAndCourt(Date date, Court court) {
        List<UserAppointment> appointments = userRepository.findAllById_Court(court);
        Calendar appointmentCalendar = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        List<UserAppointment> filteredAppointments = new ArrayList<>();

        for (UserAppointment appointment : appointments) {
            appointmentCalendar.setTime(appointment.getStartAppointmentDate());
            if (appointmentCalendar.get(Calendar.DAY_OF_MONTH) == dateCalendar.get(Calendar.DAY_OF_MONTH)
                    && appointmentCalendar.get(Calendar.MONTH) == dateCalendar.get(Calendar.MONTH)
                    && appointmentCalendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)) {
                filteredAppointments.add(appointment);
            }
        }
        return filteredAppointments;
    }

    public AppointmentPK createAppointmentPk(User user, Court court) {
        AppointmentPK appointmentPK = new AppointmentPK();

        appointmentPK.setUser(user);
        appointmentPK.setCourt(court);

        return appointmentPK;
    }
}
