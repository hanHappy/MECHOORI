package com.mechoori.web.api.dto;

import java.util.Map;

import com.mechoori.web.api.entity.enums.LoginType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
    // OAuth2 반환하는 유저 정보 ~Map~
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String username;
    private String email;

    @Builder
    public OAuthAttributes(Map<String,Object> attributes, String nameAttributeKey, String username, String email) 
    {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.username = username;
        this.email = email;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        // provider 구분
        
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                         .username((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Integer getLoginTypeId(String registrationId) {
        if ("google".equals(registrationId)) {
            return LoginType.GOOGLE.getLoginTypeId();
        } else if ("naver".equals(registrationId)) {
            return LoginType.NAVER.getLoginTypeId();
        } else if ("kakao".equals(registrationId)) {
            return LoginType.KAKAO.getLoginTypeId();
        }
        throw new IllegalArgumentException(
            "지원되지 않는 " + registrationId);
    }
}