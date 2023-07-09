package com.mechoori.web.api.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mechoori.web.api.users.LoginType;
import com.mechoori.web.api.users.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByUsername(String username);

    Optional<Member> findByRefreshToken(String refreshToken);

    /**
     * 소셜 타입과 소셜의 식별값으로 회원 찾는 메소드
     * 정보 제공을 동의한 순간 DB에 저장해야하지만, 아직 추가 정보(사는 도시, 나이 등)를 입력받지 않았으므로
     * 유저 객체는 DB에 있지만, 추가 정보가 빠진 상태이다.
     * 따라서 추가 정보를 입력받아 회원 가입을 진행할 때 소셜 타입, 식별자로 해당 회원을 찾기 위한 메소드
     */

    // OAuth2 로그인 구현 시 사용
    // 정보 제공을 통한 로그인 -> 정보를 받아 DB에 저장 -> 추가 정보 입력 시 DB에서 유저 찾기
    // by 소셜 타입 및 소셜 식별값 (정보 입력하지 않은 회원 가져오기)
    Optional<Member> findByLoginTypeAndLoginTypeId(LoginType loginType, String loginTypeId);
}
