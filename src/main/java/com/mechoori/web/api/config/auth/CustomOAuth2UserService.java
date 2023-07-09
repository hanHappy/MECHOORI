package com.mechoori.web.api.config.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.mechoori.web.api.config.auth.dto.OAuthAttributes;
import com.mechoori.web.api.entity.enums.Role;
import com.mechoori.web.api.repository.MemberApiRepository;
import com.mechoori.web.entity.Member;
import com.mechoori.web.security.MechooriUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    
    private final MemberApiRepository memberRepository;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        // System.out.println(oAuth2User);

        // OAuth2 서비스 id (구글, 카카오, 네이버)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // OAuth2 로그인 PK
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2UserService
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        String username = attributes.getUsername();
        String email = attributes.getEmail();
        String role = Role.MEMBER.getKey();
        Integer roleId = Role.MEMBER.getRoleId();
        Integer loginTypeId = attributes.getLoginTypeId(registrationId);

        Member member = memberRepository.findProfileByEmail(email);
        if(member == null){
            member = Member.builder()
                .username(username)
                .email(email)
                .role(role)
                .roleId(roleId)
                .loginTypeId(loginTypeId)
                .build();
            member = memberRepository.save(member);
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        MechooriUserDetails userDetails = new MechooriUserDetails(member, oAuth2User.getAttributes());
        userDetails.setAuthorities(authorities);

        System.out.println("로그인 한 member: " + member);
        System.out.println(authorities);

        return userDetails;
    }
}