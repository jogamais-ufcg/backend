package com.jogamais.ufcg.services;

import com.jogamais.ufcg.models.Email;
import com.jogamais.ufcg.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailService {

    @Autowired
    private EmailRepository emailRepository;


    public void send(Email email) {

    }
}
