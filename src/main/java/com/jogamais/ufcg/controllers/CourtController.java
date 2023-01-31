package com.jogamais.ufcg.controllers;


import com.jogamais.ufcg.dto.CourtDTO;
import com.jogamais.ufcg.dto.CourtResponseDTO;
import com.jogamais.ufcg.exceptions.*;
import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.services.CourtService;
import com.jogamais.ufcg.utils.errors.CourtError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/courts")
@CrossOrigin
public class CourtController implements IController {

    @Autowired
    private CourtService courtService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Court court = courtService.getById(id);
            return new ResponseEntity<>(new CourtResponseDTO(court), HttpStatus.OK);
        } catch (CourtException e) {
            return CourtError.errorUnavailableAppointment();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            courtService.deleteById(id);
            return new ResponseEntity<>("Quadra com ID: " + id + " removida com sucesso!", HttpStatus.OK);
        } catch(CourtException e) {
            return CourtError.errorCourtNotExist();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(int page) {
        Page<Court> courtsList = courtService.findAll(PageRequest.of(page, 5));
        List<CourtResponseDTO> response = courtsList.stream().map(CourtResponseDTO::new).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getByName(@PathVariable String name)  {
        try {
            Court court = courtService.getByName(name);
            return new ResponseEntity<>(new CourtResponseDTO(court), HttpStatus.OK);
        } catch (CourtException e) {
            return CourtError.errorCourtAlreadyExist();
        } catch (CourtInvalidOpeningHours e) {
            return CourtError.errorCourtInvalidOpeningHours();
        } catch (CourtInvalidRecurrenceIntervalPeriod e) {
            return CourtError.errorCourtInvalidRecurrenceIntervalPeriod();
        } catch (CourtInvalidAppointmentDuration e) {
            return CourtError.errorCourtInvalidAppointmentDuration();
        }
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CourtDTO courtDTO) {
        Court createdCourt;
        try {
            createdCourt = courtService.createCourtValidatingFields(courtDTO.getModel());
        } catch (CourtException e) {
            return CourtError.errorCourtAlreadyExist();
        } catch (CourtInvalidOpeningHours e) {
            return CourtError.errorCourtInvalidOpeningHours();
        } catch (CourtInvalidRecurrenceIntervalPeriod e) {
            return CourtError.errorCourtInvalidRecurrenceIntervalPeriod();
        } catch (CourtInvalidAppointmentDuration e) {
            return CourtError.errorCourtInvalidAppointmentDuration();
        }

        CourtResponseDTO response = new CourtResponseDTO(createdCourt);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



}
