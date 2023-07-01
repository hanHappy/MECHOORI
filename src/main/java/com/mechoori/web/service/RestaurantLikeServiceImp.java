package com.mechoori.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.RestaurantLike;
import com.mechoori.web.repository.RestaurantLikeRepository;

@Service
public class RestaurantLikeServiceImp implements RestaurantLikeService {

    @Autowired
    private RestaurantLikeRepository repository;

    @Override
    public int add(RestaurantLike restaurantlike) {
        return repository.add(restaurantlike);
    }

    @Override
    public int getCount(int restaurantId) {
        return repository.count(restaurantId);
    }

    @Override
    public int delete(RestaurantLike restaurantLike) {
        return repository.delete(restaurantLike);
    }
    
}
