package com.jogamais.ufcg.controllers;

import com.jogamais.ufcg.exceptions.InternalUserErrorException;
import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.services.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value="/users")
@CrossOrigin
public class CourtController {
    @Autowired
    private CourtService courtService;

    @RequestMapping(value = "/courts", method = RequestMethod.GET)
    public ResponseEntity<?> getById() {

    }

    @RequestMapping(value = "/courts", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Court court, UriComponentsBuilder ucBuilder) throws InternalUserErrorException {
        try {
            Court addedCourt = courtService.save(court);
            return new ResponseEntity<Court>(addedCourt, HttpStatus.OK);
        } catch (InternalUserErrorException e) {
            return CourtError.CourtNotFoundError(CourtId);
        }

    }

    @RequestMapping(value = "/courts", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete() {

        return null;
    }

    @RequestMapping(value = "/courts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById() {

        return null;
    }

    @RequestMapping(value = "/courts", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {

        return null;
    }
}
