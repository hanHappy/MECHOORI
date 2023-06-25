package com.mechoori.web.api.controller;


import com.mechoori.web.service.EmailService;
import com.mechoori.web.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sign-up")
public class SignUpController {

    @Autowired
    private EmailService service;
    @Autowired
    private SignUpService signUpService;

    @PostMapping("/emailCheck")
    @ResponseBody
    String mailConfirm(
            @RequestParam("e") String email)
            throws Exception {

        boolean emailCheck = service.confirmEmail(email);
        //logic

        if (emailCheck) {
            //중복 확인 후에 아이디가 없으면
            String code = service.sendSimpleMessage(email);
            System.out.println(email);
            System.out.println("인증코드: " + code);
            return code;
        } else
            return "0";

    }


    @PostMapping("/nicknameCheck")
    @ResponseBody
    String nickNameConfirm(
            @RequestParam("nickname") String nickname)
            throws Exception {


        boolean checknickname = signUpService.checkNickName(nickname);
        System.out.println(checknickname);

        if(checknickname) {
            return "canUse";
        }
        else{
            return "cantuse";
        }

    }

}
