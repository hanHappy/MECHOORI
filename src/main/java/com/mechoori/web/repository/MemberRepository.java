package com.mechoori.web.repository;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Member;

@Mapper
public interface MemberRepository {

    Member findById(int id);

    Member findByUsername(String username);

}
