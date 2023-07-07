package com.mechoori.web.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mechoori.web.api.entity.Member;

@Repository
public interface MemberApiRepository extends JpaRepository<Member, Long> {
    //가입여부 확인
    Optional<Member> findByEmail(String email);
}
