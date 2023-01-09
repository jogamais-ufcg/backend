package com.jogamais.ufcg.models;

import java.time.LocalDateTime;

import com.jogamais.ufcg.models.enumeration.StatusEmail;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMAIL")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmail;

    private String ownerRef;

    private String emailFrom;
    private String emailTo;

    private String subject;

    private String text;

    private LocalDateTime sendDateEmail;

    private StatusEmail statusEmail;

}
