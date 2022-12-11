package com.jogamais.ufcg.vo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Builder
public class AccountCredentialsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;
}
