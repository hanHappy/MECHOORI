package com.mechoori.web.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.mechoori.web.api.entity.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Users(Long id, String name, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Users update(String name) {
        this.name = name;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}