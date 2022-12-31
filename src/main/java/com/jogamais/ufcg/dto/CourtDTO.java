package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.CourtRules;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CourtDTO {
        private String name;
        private String photo;
        private String description;
        private CourtRules courtRules;

        public Court getModel() {
                return new Court(
                                null,
                                this.name,
                                this.photo,
                                this.description,
                                this.courtRules);
        }
}
