package com.mechoori.web.config.oauth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mechoori.web.api.entity.Member;
import com.mechoori.web.api.repository.MemberApiRepository;
import com.mechoori.web.config.oauth.dto.SessionUser;

@Service
public class PrincipalDetailService implements UserDetailsService {

    private final MemberApiRepository repository;

    public PrincipalDetailService(MemberApiRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member principal = repository.findByEmail(username).orElse(null);
        if (principal == null) {
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다");
        }
        return new SessionUser(principal);
    }
    
}