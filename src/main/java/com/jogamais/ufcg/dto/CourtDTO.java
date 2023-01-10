package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.CourtRules;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CourtDTO {
        private String name;
        private String photo;
        private String description;
        private Integer openingHour;

        private Integer closingHour;

        private Integer appointmentDuration;

        private String appointmentPeriod;

        private Integer recurrenceIntervalDate;

        private String availableDays;

        private boolean isOnlyUfcg;

        private CourtRules getCourtRules() {
                return new CourtRules(
                        null,
                        this.openingHour,
                        this.closingHour,
                        this.appointmentDuration,
                        this.appointmentPeriod,
                        this.recurrenceIntervalDate,
                        this.availableDays,
                        this.isOnlyUfcg
                );
        }

        public Court getModel() {
                return new Court(
                                null,
                                this.name,
                                this.photo,
                                this.description,
                                getCourtRules());
        }
}
