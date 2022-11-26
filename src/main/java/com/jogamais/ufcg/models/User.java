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
@Table(name="USER")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="CPF", nullable = false)
    private String cpf;

    @Column(name="EMAIL", nullable = false)
    private String email;

    @Column(name="PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    @Column(name="CONFIRMED", nullable = false)
    private Boolean isConfirmed;

    @Column(name="BLOCKED", nullable = false)
    private Boolean isBlocked;

}
