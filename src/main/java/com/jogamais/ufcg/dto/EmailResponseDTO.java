package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.Email;

import javax.validation.constraints.NotBlank;

public class EmailResponseDTO {

    @NotBlank
    private String ownerRef;

    @NotBlank
    @javax.validation.constraints.Email
    private String emailFrom;

    @NotBlank
    @javax.validation.constraints.Email
    private String emailTo;

    public EmailResponseDTO(Email email) {
        this.ownerRef = email.getOwnerRef();
        this.emailFrom = email.getEmailFrom();
        this.emailTo = email.getEmailTo();
    }
}
