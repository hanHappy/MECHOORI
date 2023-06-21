package com.mechoori.web.controller;

import com.mechoori.web.entity.Member;
import com.mechoori.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private MemberService service;

    @GetMapping("login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/login/find-id")
    public String findId() {
        return "user/login/find-id";
    }

    @GetMapping("/login/find-id-result")
    public String findIdResult() {
        return "user/login/find-id-result";
    }

    @GetMapping("/login/find-pwd")
    public String findPwd() {
        return "user/login/find-pwd";
    }

    @GetMapping("/login/find-pwd-result")
    public String findPwdResult() {
        return "user/login/find-pwd-result";
    }


    @GetMapping("sign-up/policy")
    public String policy() {
        return "user/sign-up/policy";
    }

    @GetMapping("sign-up/form")
    public String form() {
        return "user/sign-up/form";
    }

    @PostMapping("sign-up/form")
    public String form(Member member) {
        service.add(member);
        return "redirect:complete";
    }

    //내 정보
    @GetMapping("my-page")
    public String myPage(){
        return "user/my-page";
    }

    //내 정보 변경
    @GetMapping("my-page/edit-info")
    public String editInfo(){
        return "user/my-page/edit-info";
    }

    //내 휴대폰 번호 변경
    @GetMapping("my-page/edit-info/phone")
    public String editPhone() {
        return "user/my-page/edit-info/phone";
    }
    //내 이메일 주소 변경
    @GetMapping("my-page/edit-info/email")
    public String editEmail() {
        return "user/my-page/edit-info/email";
    }

    //회원탈퇴
    @GetMapping("my-page/edit-info/withdraw")
    public String withdraw() {
        return "user/my-page/edit-info/withdraw";
    }  
    // 회원탈퇴 완료 페이지
    @GetMapping("my-page/edit-info/withdraw-complete")
    public String withdrawComplete() {
        return "user/my-page/edit-info/withdraw-complete";
    }

    //찜한목록
    @GetMapping("my-page/like-list")
    public String likeList(){
        return "user/my-page/like-list";
    }

    //가성비 평가목록
    @GetMapping("my-page/statistics")
    public String rateStatistics(){
        return "user/my-page/statistics";
    }
}
