package com.mechoori.web.api.entity;

import com.mechoori.web.api.entity.enums.LoginType;
import com.mechoori.web.api.entity.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String username;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "login_type_id")
    private LoginType loginTypeId;

    @Transient
    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public Member(int id, String username, String email, String password, Role role, LoginType loginTypeId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.loginTypeId = loginTypeId;
    }

    public Member update(String username) {
        this.username = username;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginType getLoginTypeId() {
        return this.loginTypeId;
    }

    public void setLoginTypeId(LoginType loginTypeId) {
        this.loginTypeId = loginTypeId;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}