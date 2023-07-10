package com.mechoori.web.service;

import com.mechoori.web.entity.RestaurantLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Member;
import com.mechoori.web.repository.MemberRepository;

import java.util.List;

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

        repository.save(member);
    }

    @Override
    public void resetPwd(Member member) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        repository.resetPwd(member);

    }

    @Override
    public List<RestaurantLike> restaurantLike(int id) {

        repository.restaurantLIke(id);

        return null;
    }

    @Override
    public void update(Member member) {
        repository.update(member);
    }


}
