package com.jogamais.ufcg.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserEditDTO {
    private String name;
    private String phoneNumber;
}
