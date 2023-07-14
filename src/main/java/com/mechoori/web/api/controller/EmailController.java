package com.mechoori.web.api.controller;


import com.mechoori.web.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// FIXME UserController로 이동

@RestController
@RequestMapping("api/sign-up")
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/form")
    @ResponseBody
    String mailConfirm(
            @RequestParam ("e") String email)
            throws Exception {

        return service.sendSimpleMessage(email);
    }


}