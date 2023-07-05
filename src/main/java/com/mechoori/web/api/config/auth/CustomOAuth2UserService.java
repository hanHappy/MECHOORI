package com.mechoori.web.api.config.auth;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.mechoori.web.api.config.auth.dto.OAuthAttributes;
import com.mechoori.web.api.config.auth.dto.SessionUser;
import com.mechoori.web.api.entity.Member;
import com.mechoori.web.api.entity.enums.Role;
import com.mechoori.web.api.repository.MemberApiRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    
    @Autowired
    private final MemberApiRepository memberRepository;
    private final HttpSession httpSession;

    public CustomOAuth2UserService(MemberApiRepository memberRepository, HttpSession httpSession) {
        this.memberRepository = memberRepository;
        this.httpSession = httpSession;
    }
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // OAuth2 서비스 id (구글, 카카오, 네이버)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // OAuth2 로그인 진행 시 키가 되는 필드 값(PK)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2UserService
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Member member = saveOrUpdate(attributes);
        httpSession.setAttribute("member", new SessionUser(member)); // SessionUser (직렬화된 dto 클래스 사용)

        String roleKey = Role.MEMBER.getKey(); // 기본적으로 Role.MEMBER로 설정

        // 회원의 권한을 roleId를 기반으로 설정 
        if (member.getRoleId() == 2) 
            roleKey = Role.MEMBER.getKey();
         else
            roleKey = Role.ADMIN.getKey();

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(roleKey)),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    // 유저 생성 및 수정 서비스 로직
    private Member saveOrUpdate(OAuthAttributes attributes){
        Member member = memberRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getUsername()))
                .orElse(attributes.toEntity());

        member = memberRepository.saveAndFlush(member);

        // 저장된 사용자 정보를 콘솔에 출력
        System.out.println("Saved Member: " + member);
    
        // return memberRepository.save(user);
        return member;
    }

}