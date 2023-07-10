package com.mechoori.web.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mechoori.web.entity.Member;

public interface MemberApiRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Member findProfileByEmail(String email);
}