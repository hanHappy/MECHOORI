package com.mechoori.web.repository;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Member;

@Mapper
public interface MemberRepository {

    Member findById(int id);

    Member findByEmail(String email);

    String findRoleByEmail(String email);

    void save(Member member);
}
