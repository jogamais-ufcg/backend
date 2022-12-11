package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserDTO {
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean isUFCGMember;
    private Boolean isStudent;
    private Date validUntil;

    public User getModel() {
        return new User(
                null,
                this.name,
                this.cpf,
                this.email,
                this.phoneNumber,
                this.password,
                this.isUFCGMember,
                this.isStudent,
                false,
                this.validUntil,
                false,
                false
        );
    }
}

