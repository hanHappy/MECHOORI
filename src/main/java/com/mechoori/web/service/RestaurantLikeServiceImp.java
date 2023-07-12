package com.mechoori.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.LikeList;
import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.RestaurantLike;
import com.mechoori.web.repository.RestaurantLikeRepository;
import com.mechoori.web.security.MechooriUserDetails;

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

    @Override
    public List<LikeList> getList(int memberId) {
        return repository.findAll(memberId);   
    }

 
    
}
