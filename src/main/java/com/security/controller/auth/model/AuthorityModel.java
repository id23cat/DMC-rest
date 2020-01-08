package com.security.controller.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
@Setter
@Getter
public class AuthorityModel implements GrantedAuthority {

    @Id
    private String id;
    @Column
    private String type;

    @Override
    public String getAuthority() {
        return null;
    }
}
