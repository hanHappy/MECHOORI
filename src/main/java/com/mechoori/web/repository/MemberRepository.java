package com.mechoori.web.repository;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Member;

@Mapper
public interface MemberRepository {

    Member findById(int id);

    Member findByEmail(String email);

    Member findByNickname(String nickname);

    String findRoleByEmail(String email);

    void save(Member member);

    String searchEmail(String email);

    void restaurantLIke(int id);

    void resetPwd(Member member);

    Member findByImage(String img);
}
