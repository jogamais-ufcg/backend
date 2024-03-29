package com.jogamais.ufcg.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COURT")
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourt;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "PHOTO", nullable = false)
    private String photo;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "COURT_RULES_ID", referencedColumnName = "idCourtRules")
    private CourtRules courtRules;
}
