package com.mechoori.web.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mechoori.web.api.config.auth.dto.SessionUser;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("api/member")
public class MemberController {

    private final HttpSession httpSession;

    public MemberController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("/login")
    public String login(Model model){
        SessionUser member = (SessionUser) httpSession.getAttribute("member");

        if(member != null){
            model.addAttribute("userName", member.getUsername());
        }
        return "index";
    }
}
