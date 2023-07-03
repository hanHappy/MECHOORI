package com.mechoori.web.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Member;
import com.mechoori.web.service.MemberService;


@Service
public class MechooriUserDetailService implements UserDetailsService{
    @Autowired
    MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberService.getByEmail(email);
        // Users users = userService.getByEmail(email);
        MechooriUserDetails userDetails = new MechooriUserDetails();
        userDetails.setId(member.getId());
        userDetails.setGenderId(member.getGenderId());
        userDetails.setUsername(member.getUsername());
        userDetails.setNickname(member.getNickname());
        userDetails.setEmail(email);
        userDetails.setPassword(member.getPassword());
        userDetails.setBirthDate(member.getBirthDate());
        userDetails.setRegDate(member.getRegDate());
        // userDetails.setMember(users);
        
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Map<String, Object> attributes = authentication.getPrincipal().getAttributes();
            userDetails.setAttributes(attributes);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        // username 통해서 db의 member_with_role (view)에서 role_name을 String role에 할당
        String role = memberService.getRoleByEmail(email);
        // 얻어온 role을 authorities 리스트에 추가
        authorities.add(new SimpleGrantedAuthority(role));
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
    
}
