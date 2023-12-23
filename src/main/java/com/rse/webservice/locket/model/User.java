package com.rse.webservice.locket.model;

import com.rse.webservice.locket.utils.Const;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Table(name = "users")
@Entity
@Data
public class User extends AbstractAudit implements UserDetails {
    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;
    @Column(name = "locked")
    private Boolean locked;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
        locked = Boolean.FALSE;
        setStatus(Const.GeneralStatus.INACTIVE);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority granted = new SimpleGrantedAuthority(role.toString());
        return Collections.singletonList(granted);
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
        return locked;
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
