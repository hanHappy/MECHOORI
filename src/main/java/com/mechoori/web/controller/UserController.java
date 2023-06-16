package com.mechoori.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mechoori.web.entity.Member;
<<<<<<< HEAD
=======
import com.mechoori.web.service.MemberService;
>>>>>>> 775252bd50ee071f2b78c38c64b3dc2b56cb13e9

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private MemberService service;

    @GetMapping("login")
<<<<<<< HEAD
    public String login() {
        return "user/login";
    }

    @GetMapping("sign-up/policy")
    public String policy() {
=======
        public String login(){
            return "user/login";
        }

    @GetMapping("sign-up/policy")
    public String policy(){
>>>>>>> 775252bd50ee071f2b78c38c64b3dc2b56cb13e9
        return "user/sign-up/policy";
    }

    @GetMapping("sign-up/form")
<<<<<<< HEAD
    public String form() {
        return "user/sign-up/form";
    }

    @GetMapping("sign-up/form")
    public String form(Member member) {
        return "redirect:complete";
    }
=======
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

>>>>>>> 775252bd50ee071f2b78c38c64b3dc2b56cb13e9
}
