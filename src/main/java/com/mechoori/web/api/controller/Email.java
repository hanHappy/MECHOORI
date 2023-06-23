package com.mechoori.web.api.controller;

import com.mechoori.web.service.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController("apiEmailController")
@RequestMapping("api/user")
public class Email {

    @Autowired
    private MailSendService mailService;


    @PostMapping("mailcheck")
    @ResponseBody
    public String mailCheck(String email) {
        System.out.println("이메일 인증 요청이 들어옴!");
        System.out.println("이메일 인증 이메일 : " + email);
        mailService.joinEmail(email); // 이 부분은 반환값을 사용하지 않음
        return "인증 이메일이 성공적으로 발송되었습니다.";
    }

}

