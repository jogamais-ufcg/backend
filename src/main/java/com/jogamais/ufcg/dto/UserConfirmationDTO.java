package com.jogamais.ufcg.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class UserConfirmationDTO {
    private Boolean isConfirmed;

    public UserConfirmationDTO() {
    }
    public UserConfirmationDTO(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
}

