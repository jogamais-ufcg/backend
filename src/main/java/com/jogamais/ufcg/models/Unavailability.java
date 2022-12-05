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
@Table(name="UNAVAILABILITY")
public class Unavailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnavailability;

    @Column(name="STARTING_DATE", nullable = false)
    private Date startingDate;

    @Column(name="ENDING_DATE", nullable = false)
    private Date endingDate;

}
