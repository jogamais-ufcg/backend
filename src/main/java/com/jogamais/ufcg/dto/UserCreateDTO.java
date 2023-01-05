package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class UserCreateDTO {
    private String name;
    private String cpf;

    private String enrollment;
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean isUFCGMember;
    private Boolean isStudent;

    public User getModel() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date validUntil = calendar.getTime();

        return new User(
                null,
                this.name,
                this.cpf,
                this.email,
                this.enrollment,
                this.phoneNumber,
                this.password,
                this.isUFCGMember,
                this.isStudent,
                false,
                validUntil,
                false,
                false);
    }
}
