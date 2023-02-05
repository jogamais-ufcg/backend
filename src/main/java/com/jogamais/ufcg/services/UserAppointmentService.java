package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.*;
import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.models.UserAppointment;
import com.jogamais.ufcg.models.pk.AppointmentPK;
import com.jogamais.ufcg.repositories.UserAppointmentRepository;
import com.jogamais.ufcg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserAppointmentService {

    @Autowired
    private UserAppointmentRepository userRepository;

    public UserAppointment findByUserAndCourt(User user, Court court)
            throws AppointmentException, AppointmentUserOrCourtExcpetion {
        if (user == null || court == null) {
            throw new AppointmentUserOrCourtExcpetion();
        }

        return userRepository.findById_UserAndId_Court(user, court).orElseThrow(AppointmentException::new);
    }

    public UserAppointment create(UserAppointment userAppointment, User user, Court court) throws AppointmentException, UserAlreadyHasAppointmentException, InvalidAppointmentDateException {
        LocalDate startDate = userAppointment.getStartAppointmentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<UserAppointment> appointments = userRepository.findAllById_UserAndId_Court(user, court);

        for (UserAppointment appointment : appointments) {
            LocalDate appointmentStartDate = appointment.getStartAppointmentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (appointmentStartDate.getYear() == startDate.getYear() && appointmentStartDate.getMonth() == startDate.getMonth() && appointmentStartDate.getDayOfMonth() == startDate.getDayOfMonth()) {
                throw new UserAlreadyHasAppointmentException();
            }
        }

        if (userAppointment.getStartAppointmentDate().before(new Date())) {
            throw new InvalidAppointmentDateException();
        }

        if (this.isAppointmentWithinExistingInterval(userAppointment)) {
            throw new AppointmentException();
        }

        LocalTime openingHour = LocalTime.of(court.getCourtRules().getOpeningHour(), 0);
        LocalTime closingHour = LocalTime.of(court.getCourtRules().getClosingHour(), 0);
        return userRepository.save(userAppointment);
    }

    public void deleteByUserAndCourt(User user, Court court)
            throws AppointmentException, AppointmentUserOrCourtExcpetion, CancellationNotAllowedException {
        UserAppointment userAppointment = findByUserAndCourt(user, court);

        if (this.isWithInTwoDays(userAppointment.getStartAppointmentDate())) {
            throw new CancellationNotAllowedException();
        }

        userRepository.delete(userAppointment);
    }

    private boolean isWithInTwoDays(Date startAppointmentDate) {
        LocalDate startDate = LocalDateTime.ofInstant(startAppointmentDate.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(today, startDate);

        return daysBetween <= 2;
    }


    public Page<UserAppointment> search(String searchTerm, int page, int size) {
        return null;
    }


    public Page<UserAppointment> findAll(PageRequest page) {
        Page<UserAppointment> userAppointmentList = userRepository.findAll(PageRequest.of(page.getPageNumber(), 10));

        return userAppointmentList;
    }

    public List<UserAppointment> findAppointmentsByDayAndCourt(Date date, Court court) throws NoAppointmentsException {
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

        if (filteredAppointments.isEmpty()) {
            throw new NoAppointmentsException();
        }

        return filteredAppointments;
    }

    public boolean isAppointmentWithinExistingInterval(UserAppointment newAppointment) {
        List<UserAppointment> existingAppointments = userRepository.findAllById_Court(newAppointment.getId().getCourt());
        for (UserAppointment existingAppointment : existingAppointments) {
            if (newAppointment.getStartAppointmentDate().after(existingAppointment.getStartAppointmentDate())
                    && newAppointment.getStartAppointmentDate().before(existingAppointment.getEndAppointmentDate())) {
                return true;
            }
            if (newAppointment.getStartAppointmentDate().equals(existingAppointment.getStartAppointmentDate())
                    && newAppointment.getEndAppointmentDate().equals(existingAppointment.getEndAppointmentDate())) {
                return true;
            }
        }
        return false;
    }


    public AppointmentPK createAppointmentPk(User user, Court court) {
        AppointmentPK appointmentPK = new AppointmentPK();

        appointmentPK.setUser(user);
        appointmentPK.setCourt(court);

        return appointmentPK;
    }

    public List<UserAppointment> getMyAppointments(User user) throws NoAppointmentsException {
        List<UserAppointment> appointments = userRepository.findAllById_User(user);

        if (appointments.isEmpty()) {
            throw new NoAppointmentsException();
        }

        return appointments;
    }
}
