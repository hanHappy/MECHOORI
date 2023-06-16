package com.mechoori.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Member;
import com.mechoori.web.service.MemberService;

@Service
public class MechooriUserDetailService implements UserDetailsService{
    @Autowired
    MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.getByUsername(username);
        MechooriUserDetails userDetails = new MechooriUserDetails();
        userDetails.setId(member.getId());
        userDetails.setUsername(username);
        userDetails.setPassword(member.getPassword());
        userDetails.setNickname(member.getNickname());
        // userDetails.setEmail(member.getEmail());
        userDetails.setPhoneNumber(member.getPhoneNumber());
        userDetails.setGenderId(member.getGenderId());
        userDetails.setBirthDate(member.getBirthDate());
        userDetails.setRegDate(member.getRegDate());
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        // username 통해서 db의 member_with_role (view)에서 role_name을 String role에 할당
        String role = memberService.getRoleByUserName(username);
        // 얻어온 role을 authorities 리스트에 추가
        authorities.add(new SimpleGrantedAuthority(role));
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
    
}
