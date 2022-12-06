package com.jogamais.ufcg.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/courts")
@CrossOrigin
public class CourtController {

    @RequestMapping(value = "/court", method = RequestMethod.GET)
    public ResponseEntity<?> getById() {

        return null;
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
