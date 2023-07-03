package com.mechoori.web.api.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "비회원"),
    MEMBER("ROLE_MEMBER", "회원");

    private final String key;
    private final String title;
} 