package com.mechoori.web.repository;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.MemberWithRoleView;

@Mapper
public interface MemberRepository {

    Member findById(int id);

    Member findByUsername(String username);

    String findRoleByUsername(String username);
}
