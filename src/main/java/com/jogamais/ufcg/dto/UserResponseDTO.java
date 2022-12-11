package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserResponseDTO {
    private final String name;
    private final String cpf;
    private final String email;
    private final String phoneNumber;
    private final Boolean isUFCGMember;
    private final Boolean isStudent;
    private final Date validUntil;

    public UserResponseDTO(User user) {
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.isUFCGMember = user.getIsUFCGMember();
        this.isStudent = user.getIsStudent();
        this.validUntil = user.getValidUntil();
    }
}
