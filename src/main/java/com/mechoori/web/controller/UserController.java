package com.mechoori.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mechoori.web.entity.Member;
import com.mechoori.web.service.MemberService;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private MemberService service;

    @GetMapping("login")
        public String login(){
            return "user/login";
        }

    @GetMapping("sign-up/policy")
    public String policy(){
        return "user/sign-up/policy";
    }

    @GetMapping("sign-up/form")
    public String form(){
        return "user/sign-up/form";
    }

    @PostMapping("sign-up/form")
    public String form(Member member){
        service.add(member);
        return "redirect:complete";
    }

    @GetMapping("my-page/edit-info/phone")
    public String editPhone(){
        return "user/my-page/edit-info/phone";
    }

    @GetMapping("my-page/edit-info/email")
    public String editEmail(){
        return "user/my-page/edit-info/email";
    }

    @GetMapping("my-page/edit-info/withdraw")
    public String withdraw(){
        return "user/my-page/edit-info/withdraw";
    }

}
