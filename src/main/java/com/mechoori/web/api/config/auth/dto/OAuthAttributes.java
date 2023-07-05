package com.mechoori.web.api.config.auth.dto;

import java.util.Map;

import com.mechoori.web.api.entity.Member;
import com.mechoori.web.api.entity.enums.Role;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
    // OAuth2 반환하는 유저 정보 Map
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String username;
    private String nickname;
    private String email;
    // private String img;

    @Builder
    public OAuthAttributes(Map<String,Object> attributes, String nameAttributeKey, String username, String nickname, String email) 
    //, String img
    {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        // this.img = img;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        // provider 구분
        
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .username((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                // .img((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity(){
        return Member.builder()
                .username(username)
                .email(email)
                // .img(img)
                .role(Role.MEMBER) // 기본 권한 Member
                .build();
    }
}
