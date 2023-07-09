package com.mechoori.web.api.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", 3),
    MEMBER("ROLE_MEMBER", 2),
    ADMIN("ROLE_ADMIN", 1);

    private final String key;
    private final Integer roleId;
}
