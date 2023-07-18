package com.mechoori.web.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mechoori.web.service.EmailService;


@RestController
@RequestMapping("api")
public class FindAccountController {


    @Autowired
    private EmailService service;

    @PostMapping("find-pwd/email-check")
    @ResponseBody
    String mailConfirm(
            @RequestParam("email") String email)
            throws Exception {

        boolean emailCheck = service.findAccount(email);
        //not null

        if (emailCheck) {

            System.out.println(email);
            String code = service.sendSimpleMessage(email);
            return code;

        } else {
            return "cant";
        }
    }


    @PostMapping("/find-pwd")
    public String resetPwd(
            @RequestParam("email") String email){


        System.out.println(email);
        return email;
    }



}