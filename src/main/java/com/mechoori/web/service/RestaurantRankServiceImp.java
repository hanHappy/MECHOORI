package com.mechoori.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mechoori.web.entity.RestaurantRankView;
import com.mechoori.web.repository.RestaurantRepository;

@Service
public class RestaurantRankServiceImp implements RestaurantRankService {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public List<RestaurantRankView> getRankTop5() {

        return repository.findRankAll(5, null);
    }
    
}
