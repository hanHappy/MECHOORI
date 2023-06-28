package com.mechoori.web.api.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;

//import com.mechoori.web.api.service.UserService;

@RestController("apiUserController")
@RequestMapping("api/users")
public class UserController {
    

    @Autowired
    private ViewResolver viewResolver;

    @GetMapping("/login")
    public ModelAndView login() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(viewResolver.resolveViewName("user/login/social-login", Locale.getDefault()));
        return modelAndView;
    }
    //private UserService userService;

    // @GetMapping("/login")
    // public String login() {
    //     return "user/login/social-login";
    // }
}