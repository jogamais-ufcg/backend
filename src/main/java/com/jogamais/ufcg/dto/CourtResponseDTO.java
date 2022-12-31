package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.CourtRules;
import lombok.Getter;

@Getter
public class CourtResponseDTO {
    private final String name;
    private final String photo;
    private final String description;
    private final CourtRules courtRules;


    public CourtResponseDTO(Court court) {
        this.name = court.getName();
        this.photo = court.getPhoto();
        this.description = court.getDescription();
        this.courtRules = court.getCourtRules();
    }
}
