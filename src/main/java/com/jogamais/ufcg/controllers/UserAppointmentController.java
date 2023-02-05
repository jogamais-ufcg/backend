package com.jogamais.ufcg.controllers;

import com.jogamais.ufcg.dto.UserAppointmentDTO;
import com.jogamais.ufcg.dto.UserAppointmentResponseDTO;
import com.jogamais.ufcg.exceptions.*;
import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.CourtRules;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.models.UserAppointment;
import com.jogamais.ufcg.models.pk.AppointmentPK;
import com.jogamais.ufcg.services.CourtService;
import com.jogamais.ufcg.services.UserAppointmentService;
import com.jogamais.ufcg.services.UserService;
import com.jogamais.ufcg.utils.DateConverter;
import com.jogamais.ufcg.utils.errors.AppointmentError;
import com.jogamais.ufcg.utils.errors.CourtError;
import com.jogamais.ufcg.utils.errors.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/appointments/users")
@CrossOrigin
public class UserAppointmentController implements IController {

    @Autowired
    private UserAppointmentService userAppointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourtService courtService;

    @RequestMapping(value = "/{idUser}/courts/{idCourt}", method = RequestMethod.GET)
    public ResponseEntity<?> findByUserAndCourt(@PathVariable Long idUser, @PathVariable Long idCourt)
            throws UserException, CourtException {
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
            UserAppointment userAppointment = userAppointmentService.findByUserAndCourt(user, court);
            return new ResponseEntity<>(new UserAppointmentResponseDTO(userAppointment), HttpStatus.OK);
        } catch (AppointmentException e) {
            return AppointmentError.errorAppointmentNotExist();
        } catch (AppointmentUserOrCourtExcpetion e) {
            return AppointmentError.errorAppointmentUserOrCourt();
        }
    }

    @RequestMapping(value = "/{idUser}/courts/{idCourt}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteByUserAndCourt(@PathVariable Long idUser, @PathVariable Long idCourt)
            throws CourtException, UserException {
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
            userAppointmentService.deleteByUserAndCourt(user, court);
            return new ResponseEntity<>("Agendamento removido com sucesso!", HttpStatus.OK);
        } catch (AppointmentException e) {
            return AppointmentError.errorAppointmentNotExist();
        } catch (AppointmentUserOrCourtExcpetion e) {
            return AppointmentError.errorAppointmentUserOrCourt();
        } catch (CancellationNotAllowedException e) {
            return AppointmentError.errorAppointmentCancellationNotAllowed();
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(int page) {
        Page<UserAppointment> userAppointmentList = userAppointmentService.findAll(PageRequest.of(page, 10));
        List<UserAppointmentResponseDTO> response = userAppointmentList.stream().map(UserAppointmentResponseDTO::new)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserAppointmentDTO userAppointmentDTO, @RequestParam Long idUser,
            @RequestParam Long idCourt) throws UserException, CourtException {
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
        UserAppointment createdUserAppointment = userAppointmentDTO.getModel();
        Integer appointmentDuration = court.getCourtRules().getAppointmentDuration();
        createdUserAppointment.setAppointmentInterval(userAppointmentDTO.getStartAppointmentDate(), appointmentDuration);
        createdUserAppointment.setId(appointmentPK);
        try {
            userAppointmentService.create(createdUserAppointment, user, court);
        } catch (AppointmentException e) {
            return AppointmentError.errorAppointmentTimeUnavailable();
        } catch (UserAlreadyHasAppointmentException e) {
            return UserError.errorAppointmentUserHasExisting();
        }

        UserAppointmentResponseDTO response = new UserAppointmentResponseDTO(createdUserAppointment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/courts/{idCourt}/day-and-court", method = RequestMethod.GET)
    public ResponseEntity<?> getAppointmentsByDayAndCourt(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable Long idCourt)
            throws CourtException, NoAppointmentsException {
        Court court;
        try {
            court = courtService.getById(idCourt);
        } catch (CourtException e) {
            return CourtError.errorCourtNotExist();
        }

        List<UserAppointment> appointments;
        try {
            appointments = userAppointmentService.findAppointmentsByDayAndCourt(date, court);
        } catch (NoAppointmentsException e) {
            return AppointmentError.errorAppointmentCourtUnavailable();
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @RequestMapping(value = "/my-appointments", method = RequestMethod.GET)
    public ResponseEntity<?> getMyAppointments(@RequestParam("id") Long idUser) throws UserException, NoAppointmentsException {

        List<UserAppointment> appointments;
        User user;
        try {
            user = userService.getById(idUser);
            appointments = userAppointmentService.getMyAppointments(user);
        } catch (NoAppointmentsException e) {
            return AppointmentError.errorAppointmentsNotExist();
        } catch (UserException e) {
            return UserError.errorUserNotExist();
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
