package com.jogamais.ufcg.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private Boolean authenticated;

    private Date created;

    private Date expiration;

    private String accessToken;

    private String refreshToken;
}