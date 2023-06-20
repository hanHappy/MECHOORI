package com.mechoori.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Rate;
import com.mechoori.web.repository.RateRepository;

@Service
public class RateServiceImp implements RateService{
    
    @Autowired
    private RateRepository repository;

    @Override
    public void add(Rate rate, int userId) {

        rate.setId(null);
        rate.setUserId(userId);

        repository.add(rate);
    }
    
}
