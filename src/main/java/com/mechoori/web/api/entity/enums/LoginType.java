package com.mechoori.web.api.entity.enums;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Entity
@Table(name = "member")
public enum LoginType {
    GOOGLE(2),
    NAVER(3),
    KAKAO(4);

    @Id
    private final int id;

    @Column(name = "login_type_id")
    private final int loginTypeId;

    private LoginType(int id) {
        this.id = id;
        this.loginTypeId = id;
    }
 
}
