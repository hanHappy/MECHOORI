package com.mechoori.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.LikeList;
import com.mechoori.web.repository.LikeListRepository;

@Service
public class LikeListServiceImp implements LikeListService {

    @Autowired
    private LikeListRepository repository;

    @Override
    public List<LikeList> getList(int memberId) {

        List<LikeList> list = repository.findAll(memberId);

        return list;

    }
    
}
