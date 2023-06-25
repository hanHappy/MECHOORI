package com.mechoori.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Member;
import com.mechoori.web.repository.MemberRepository;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Override
    public Member getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Member getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public String getRoleByEmail(String email) {
        return repository.findRoleByEmail(email);
    }

    @Override
    public void add(Member member) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        //이메일 인증 요청시 db에 아이디 중복확인 하고 >>> 번호주거나 말거나.....

        repository.save(member);
    }


}
