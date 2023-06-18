package com.mechoori.web.Rest;


import com.mechoori.web.service.MailService;
import com.mechoori.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private MailService service;
    @Autowired
    private MemberService memberService;






}
