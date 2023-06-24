package com.mechoori.web.api.controller;


import com.mechoori.web.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class EmailController {

    @Autowired
    private EmailService service;

    // 이메일 인증
    @PostMapping("/mailcheck")
    @ResponseBody
    String mailConfirm(
            @RequestParam ("e") String email)
            throws Exception {

        String code = service.sendSimpleMessage(email);
        System.out.println(email);
        System.out.println("인증코드: " + code);
        return code;
    }




}
