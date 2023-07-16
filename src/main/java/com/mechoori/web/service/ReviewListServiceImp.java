package com.mechoori.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.ReviewListView;
import com.mechoori.web.repository.ReviewListRepository;

@Service
public class ReviewListServiceImp implements ReviewListService{

    @Autowired
    private ReviewListRepository repository;


    @Override
    public List<ReviewListView> getDate(int member) {
       
        List<ReviewListView> list = repository.findAll(member);

        return list;
    }
    
}
