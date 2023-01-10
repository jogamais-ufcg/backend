package com.jogamais.ufcg.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="COURT_RULES")
public class CourtRules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourtRules;

    @Column(name="OPENING_HOUR", nullable = false)
    private Integer openingHour;

    @Column(name="CLOSING_HOUR", nullable = false)
    private Integer closingHour;

    @Column(name="APPOINTMENT_DURATION", nullable = false)
    private Integer appointmentDuration;

    @Column(name="APPOINTMENT_PERIOD", nullable = false)
    private String appointmentPeriod;

    @Column(name="RECURRENCE_INTERVAL_PERIOD", nullable = false)
    private Integer recurrenceIntervalPeriod;

    @Column(name="AVAILABLE_DAYS", nullable = false)
    private String availableDays;

    @Column(name="IS_ONLY_UFCG", nullable = false)
    private boolean isOnlyUfcg;
}
