package com.mechoori.web.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mechoori.web.api.entity.Member;
import com.mechoori.web.api.entity.enums.LoginType;

public interface MemberApiRepository extends JpaRepository<Member, Long> {
    // Member findByEmailAndPasswordAndLoginTypeId(String email, String password, LoginType loginTypeId);
    Optional<Member> findByEmail(String email);
    boolean existsUsersByEmail(String email);
}
