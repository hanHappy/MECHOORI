package com.mechoori.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.RestaurantDetail;
import com.mechoori.web.repository.RestaurantDetailRepository;

@Service
public class RestaurantDetailServiceImp implements RestaurantDetailService{

    @Autowired
    RestaurantDetailRepository repository;

    @Override
    public RestaurantDetail getDetail(int restaurantId) {

        return repository.findById(restaurantId);
    }
    
}
