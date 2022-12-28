package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.AppointmentException;
import com.jogamais.ufcg.models.AdminAppointment;
import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.models.pk.AppointmentPK;
import com.jogamais.ufcg.repositories.AdminAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminAppointmentService implements IService<AdminAppointment> {

    @Autowired
    private AdminAppointmentRepository adminRepository;

    public AdminAppointment findByUserAndCourt(User user, Court court) throws AppointmentException {
        if (user == null || court == null) {
            throw new AppointmentException();
        }

        return adminRepository.findById_UserAndId_Court(user, court).orElseThrow(AppointmentException::new);
    }


    @Override
    public AdminAppointment create(AdminAppointment adminAppointment) {
        return adminRepository.save(adminAppointment);
    }

    public void deleteById(Long id) throws AppointmentException {
        AdminAppointment adminAppointment = null; // getByID;
        adminRepository.delete(adminAppointment);

    }

    @Override
    public Page<AdminAppointment> search(String searchTerm, int page, int size) {
        return null;
    }

    @Override
    public Page<AdminAppointment> findAll(PageRequest page) {
        Page<AdminAppointment> adminAppointmentsList = adminRepository.findAll(PageRequest.of(page.getPageNumber(), 10));

        return adminAppointmentsList;
    }

    public AppointmentPK createAppointmentPk(User user, Court court) {
        AppointmentPK appointmentPK = new AppointmentPK();

        appointmentPK.setUser(user);
        appointmentPK.setCourt(court);

        return appointmentPK;
    }
}
