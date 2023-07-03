package com.mechoori.web.api.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.mechoori.web.api.entity.Member;
import com.mechoori.web.api.repository.MemberApiRepository;
import com.mechoori.web.config.oauth.dto.OAuthAttributes;
import com.mechoori.web.config.oauth.dto.SessionUser;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
 
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
 
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
 
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
 
        Member member = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(member));
 
        return new DefaultOAuth2User(
                // Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBER")),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }
 
    private Member saveOrUpdate(OAuthAttributes attributes) {
        Member member = memberRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getUsername()))
                .orElse(attributes.toEntity());
 
        member = memberRepository.save(member);

    // 저장된 사용자 정보를 콘솔에 출력
    System.out.println("Saved Member: " + member);

    // return memberRepository.save(user);
        return member;
    }
}
