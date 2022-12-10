package com.jogamais.ufcg.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/default")
public interface IController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getById(@PathVariable Long id);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteById(@PathVariable Long id);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ResponseEntity<?> findAll();
}
