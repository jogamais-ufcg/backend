package com.jogamais.ufcg.controllers;

import com.jogamais.ufcg.dto.AdminAppointmentDTO;
import com.jogamais.ufcg.dto.AdminAppointmentResponseDTO;
import com.jogamais.ufcg.dto.UserAppointmentDTO;
import com.jogamais.ufcg.dto.UserAppointmentResponseDTO;
import com.jogamais.ufcg.exceptions.AppointmentException;
import com.jogamais.ufcg.exceptions.CourtException;
import com.jogamais.ufcg.exceptions.UserException;
import com.jogamais.ufcg.models.AdminAppointment;
import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.models.UserAppointment;
import com.jogamais.ufcg.models.pk.AppointmentPK;
import com.jogamais.ufcg.services.CourtService;
import com.jogamais.ufcg.services.UserAppointmentService;
import com.jogamais.ufcg.services.UserService;
import com.jogamais.ufcg.utils.AppointmentError;
import com.jogamais.ufcg.utils.CourtError;
import com.jogamais.ufcg.utils.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/appointments/user")
@CrossOrigin
public class UserAppointmentController implements IController{

    @Autowired
    private UserAppointmentService userAppointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourtService courtService;

    @RequestMapping(value = "/{idUser}/{idCourt}", method = RequestMethod.GET)
    public ResponseEntity<?> findByUserAndCourt(@PathVariable Long idUser, @PathVariable Long idCourt) throws UserException, CourtException {
        User user;
        Court court;
        try {
            user = userService.getById(idUser);
            court = courtService.getById(idCourt);
        } catch (UserException e) {
            return UserError.errorUserNotExist();
        } catch (CourtException e) {
            return CourtError.errorCourtNotExist();
        }
        try {
            AdminAppointment adminAppointment = userAppointmentService.findByUserAndCourt(user, court);
            return new ResponseEntity<>(new AdminAppointmentResponseDTO(adminAppointment), HttpStatus.OK);
        } catch (AppointmentException e) {
            return AppointmentError.errorAppointmentNotExist();
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(Long id) {
        try {
            userAppointmentService.deleteById(id);
            return new ResponseEntity<>("Agendamento com ID: " + id + " removido com sucesso!", HttpStatus.OK);
        } catch(AppointmentException e) {
            return AppointmentError.errorAppointmentNotExist();
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(int page) {
        Page<UserAppointment> userAppointmentList = userAppointmentService.findAll(PageRequest.of(page, 10));
        List<UserAppointmentResponseDTO> response = userAppointmentList.stream().map(UserAppointmentResponseDTO::new).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserAppointmentDTO userAppointmentDTO, @RequestParam Long idUser, @RequestParam Long idCourt) throws UserException, CourtException {
        User user;
        Court court;
        try {
            user = userService.getById(idUser);
            court = courtService.getById(idCourt);
        } catch (UserException e) {
            return UserError.errorUserNotExist();
        } catch (CourtException e) {
            return CourtError.errorCourtNotExist();
        }

        AppointmentPK appointmentPK = userAppointmentService.createAppointmentPk(user, court);
        UserAppointment createdUserAppointment = userAppointmentService.create(userAppointmentDTO.getModel());
        createdUserAppointment.setId(appointmentPK);
        UserAppointmentResponseDTO response = new UserAppointmentResponseDTO(createdUserAppointment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
