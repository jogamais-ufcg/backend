package com.jogamais.ufcg.controllers;

import com.jogamais.ufcg.dto.EmailDTO;
import com.jogamais.ufcg.dto.EmailResponseDTO;
import com.jogamais.ufcg.models.Email;
import com.jogamais.ufcg.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/emails")
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid EmailDTO emailDTO)  {

        Email createdEmail = emailDTO.getModel();
        emailService.send(createdEmail);

        EmailResponseDTO response = new EmailResponseDTO(createdEmail);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
