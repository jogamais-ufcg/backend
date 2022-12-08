package com.jogamais.ufcg.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/default")
public interface IController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@RequestParam Long id);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@RequestParam Long id);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> findAll();
}
