package com.jogamais.ufcg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourtRulesEditDTO {

    private String name;
    private String photo;
    private String description;
    private Boolean isOnlyUfcg;
    private Integer openingHour;
    private Integer closingHour;
    private Integer appointmentDuration;
    private String appointmentPeriod;
    private String availableDays;
}
