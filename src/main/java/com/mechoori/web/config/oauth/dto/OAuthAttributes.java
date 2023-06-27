package com.mechoori.web.config.oauth.dto;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechoori.web.api.entity.Users;
import com.mechoori.web.api.entity.enums.Role;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private static final RestTemplate restTemplate = new RestTemplate();

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {

        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        } else if ("kakao".equals(registrationId)) {
            return ofKakao("id", attributes);
        }
 
        return ofGoogle(userNameAttributeName, attributes);
    }
 
    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
 
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
 
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .build();
    }
 
    //     private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
    //     if (attributes == null) {
    //         throw new IllegalArgumentException("Attributes는 null일 수 없습니다.");
    //     }

    //     // Kakao API 요청
    //     String accessToken = (String) attributes.get("accessToken");
    //     String apiUrl = "https://kapi.kakao.com/v2/user/me";

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.set("Authorization", "Bearer " + accessToken);
    //     HttpEntity<String> entity = new HttpEntity<>(headers);

    //     ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

    //     if (responseEntity.getStatusCode().is2xxSuccessful()) {
    //         // Kakao API 응답 처리
    //         ObjectMapper objectMapper = new ObjectMapper();
    //         JsonNode responseNode;
    //         try {
    //             responseNode = objectMapper.readTree(responseEntity.getBody());
    //         } catch (JsonProcessingException e) {
    //             throw new RuntimeException("Kakao 응답을 파싱하는 데 실패했습니다.");
    //         }

    //         JsonNode kakaoAccountNode = responseNode.get("kakao_account");
    //         JsonNode profileNode = responseNode.get("profile");

    //         if (kakaoAccountNode == null || profileNode == null) {
    //             throw new RuntimeException("Kakao 응답이 잘못되었습니다.");
    //         }

    //         // OAuthAttributes 객체 생성
    //         return OAuthAttributes.builder()
    //                 .name(profileNode.get("nickname").asText())
    //                 .email(kakaoAccountNode.get("email").asText())
    //                 .attributes(attributes)
    //                 .nameAttributeKey(userNameAttributeName)
    //                 .build();
    //     } else {
    //         throw new RuntimeException("Kakao 사용자 정보를 가져오는 데 실패했습니다.");
    //     }
    // }
 
    public Users toEntity() {
        return Users.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }
}