package com.mechoori.web.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mechoori.web.api.service.UserService;

@Controller("apiUserController")
@RequestMapping("api/users")
public class UserController {
    
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "user/login/social-login";
    }
}
