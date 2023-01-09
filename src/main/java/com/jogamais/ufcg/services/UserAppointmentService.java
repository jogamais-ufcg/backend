package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.AppointmentException;
import com.jogamais.ufcg.exceptions.AppointmentUserOrCourtExcpetion;
import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.models.UserAppointment;
import com.jogamais.ufcg.models.pk.AppointmentPK;
import org.springframework.beans.factory.annotation.Autowired;
import com.jogamais.ufcg.repositories.UserAppointmentRepository;
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

    public List<Date> listAvailableTimes(List<UserAppointment> appointments) {
        Calendar calendar = Calendar.getInstance();

        List<Date> availableTimes = new ArrayList<>();
        for (UserAppointment appointment : appointments) {
            Date startDate = appointment.getStartAppointmentDate();
            Date endDate = appointment.getEndAppointmentDate();
            calendar.setTime(startDate);

            long durationInMilliseconds = endDate.getTime() - startDate.getTime();
            int durationInHours = (int) (durationInMilliseconds / (1000 * 60 * 60));

            while (calendar.getTime().before(endDate)) {
                Date startTime = calendar.getTime();
                calendar.add(Calendar.HOUR_OF_DAY, durationInHours);
                Date endTime = calendar.getTime();

                boolean isAvailable = true;
                for (UserAppointment a : appointments) {
                    if (a.getId().equals(appointment.getId())) {
                        continue;
                    }
                    if (!(endTime.before(a.getStartAppointmentDate()) || startTime.after(a.getEndAppointmentDate()))) {
                        isAvailable = false;
                        break;
                    }
                }

                if (isAvailable) {
                    availableTimes.add(startTime);
                }
            }
        }

        return availableTimes;
    }

    public AppointmentPK createAppointmentPk(User user, Court court) {
        AppointmentPK appointmentPK = new AppointmentPK();

        appointmentPK.setUser(user);
        appointmentPK.setCourt(court);

        return appointmentPK;
    }
}
