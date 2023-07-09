package com.mechoori.web.api.entity.enums;

public enum LoginType {
    GOOGLE(2),
    NAVER(3),
    KAKAO(4);

    private final int loginTypeId;

    LoginType(int loginTypeId) {
        this.loginTypeId = loginTypeId;
    }

    public int getLoginTypeId() {
        return loginTypeId;
    }
}