package com.mechoori.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.MemberWithRoleView;
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
    public Member getByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public String getRoleByUserName(String username) {
        return repository.findRoleByUsername(username);
    }

}
