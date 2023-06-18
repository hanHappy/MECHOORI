package com.mechoori.web.controller;

import com.mechoori.web.entity.KakaoMember;
import com.mechoori.web.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class KakaoLoginController {

    @Autowired
    private KakaoService memberService;

    @PostMapping("/kakao-login")
    public ResponseEntity<String> kakaoLogin(@RequestBody KakaoMember kakaoMember) {
        // Kakao 로그인 요청 데이터 처리
        String kakaoId = kakaoMember.getKakaoId();
        String username = kakaoMember.getUsername();
        String email = kakaoMember.getEmail();
        int genderId = kakaoMember.getGenderId();

        // 로그인 로직 구현
        boolean isAuthenticated = memberService.performKakaoLogin(kakaoId, email);

        if (isAuthenticated) {
            // 로그인 성공 응답 생성
            String message = "Kakao 로그인 성공 - " + username;
            return ResponseEntity.ok(message);
        } else {
            // 로그인 실패 응답 생성
            String errorMessage = "Kakao 로그인 실패";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}