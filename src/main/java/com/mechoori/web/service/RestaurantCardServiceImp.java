package com.mechoori.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.RestaurantCard;
import com.mechoori.web.repository.RestaurantCardRepository;

@Service
public class RestaurantCardServiceImp implements RestaurantCardService{
    @Autowired
    RestaurantCardRepository repository;

    @Override
    public List<RestaurantCard> getListByCtgId(int categoryId) {
        return repository.findAllByCtgId(categoryId);
    }

    @Override
    public List<RestaurantCard> getListByQuery(String query) {
        return repository.findAllByQuery(query);
    }
    
}
