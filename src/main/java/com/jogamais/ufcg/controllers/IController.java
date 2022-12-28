package com.jogamais.ufcg.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/default")
public interface IController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ResponseEntity<?> findAll(int page);
}
