package com.jogamais.ufcg.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "ENROLLMENT")
    private String enrollment;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Permission> permissions = new ArrayList<>();

    @Column(name = "IS_UFCG_MEMBER", nullable = false)
    private Boolean isUFCGMember;

    @Column(name = "IS_STUDENT", nullable = false)
    private Boolean isStudent;

    @Column(name = "IS_ADMIN", nullable = false)
    private Boolean isAdmin;

    @Column(name = "VALID_UNTIL", nullable = false)
    private Date validUntil;

    @Column(name = "CONFIRMED", nullable = false)
    private Boolean isConfirmed;

    @Column(name = "BLOCKED", nullable = false)
    private Boolean isBlocked;
}
