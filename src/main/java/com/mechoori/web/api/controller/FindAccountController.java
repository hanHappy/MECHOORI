package com.mechoori.web.api.controller;

import com.mechoori.web.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

        System.out.println(emailCheck);

        if (emailCheck) {
            String code = service.sendSimpleMessage(email);
            System.out.println(email);
            System.out.println("인증코드: " + code);
            return code;

        } else {
            return "cant";
        }
    }


    @PostMapping("/find-pwd")
    public String resetPwd(
            @RequestParam("email") String email){

        return email;
    }



}
