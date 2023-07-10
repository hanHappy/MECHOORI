package com.mechoori.web.service;

import com.mechoori.web.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SignUpServiceImp implements SignUpService {

    @Autowired
    private SignUpRepository repository;

    @Override
    public boolean checkNickName(String nickname) {

        String checknickname = repository.checkNickName(nickname);

        return checknickname==null;
    }
}
