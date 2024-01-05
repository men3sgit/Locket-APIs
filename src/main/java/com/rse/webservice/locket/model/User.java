package com.rse.webservice.locket.model;

import com.rse.webservice.locket.constants.Const;
import com.rse.webservice.locket.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Table(name = "users")
@Entity
@Data
public class User extends AbstractAudit implements UserDetails {
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "roles")
    private String roles;

    public User() {
        this.locked = Boolean.FALSE;
        this.roles = Role.ROLE_USER.name();
        setStatus(Const.GeneralStatus.INACTIVE);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(roles.split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isEnabled() {
        return this.getStatus() == Const.GeneralStatus.ACTIVE;
    }
}
