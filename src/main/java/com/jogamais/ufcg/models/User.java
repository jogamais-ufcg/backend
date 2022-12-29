package com.jogamais.ufcg.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="USER_NAME", unique = true)
    private String userName;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    @Column(name = "ACCOUNT_NON_EXPIRED")
    private Boolean accountNonExpired;

    @Column(name = "ACCOUNT_NON_LOCKED")
    private Boolean accountNonLocked;

    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private Boolean credentialsNonExpired;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission", joinColumns = {@JoinColumn (name = "ID_USER")},
            inverseJoinColumns = {@JoinColumn (name = "ID_PERMISSION")}
    )
    private List<Permission> permissions;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="CPF", nullable = false)
    private String cpf;

    @Column(name="EMAIL", nullable = false)
    private String email;

    @Column(name="ENROLLMENT")
    private String enrollment;

    @Column(name="PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name="IS_UFCG_MEMBER", nullable = false)
    private Boolean isUFCGMember;

    @Column(name="IS_STUDENT", nullable = false)
    private Boolean isStudent;

    @Column(name="IS_ADMIN")
    private Boolean isAdmin = false;

    @Column(name="VALID_UNTIL", nullable = false)
    private Date validUntil;

    @Column(name="CONFIRMED", nullable = false)
    private Boolean isConfirmed;

    @Column(name="BLOCKED", nullable = false)
    private Boolean isBlocked;

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (Permission permission : permissions) {
            roles.add(permission.getDescription());
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
