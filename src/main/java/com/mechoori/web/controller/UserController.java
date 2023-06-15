package com.mechoori.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mechoori.web.entity.Member;

@Controller
@RequestMapping("user")
public class UserController {
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
        System.out.println(member);
        return "redirect:complete";
    }

}
