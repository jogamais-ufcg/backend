package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.CourtRules;
import lombok.Getter;

@Getter
public class CourtResponseDTO {

    private final Long idCourt;
    private final String name;
    private final String photo;
    private final String description;
    private final CourtRules courtRules;


    public CourtResponseDTO(Court court) {
        this.idCourt = court.getIdCourt();
        this.name = court.getName();
        this.photo = court.getPhoto();
        this.description = court.getDescription();
        this.courtRules = court.getCourtRules();
    }
}
