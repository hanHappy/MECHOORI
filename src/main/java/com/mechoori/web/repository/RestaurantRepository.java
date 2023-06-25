package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantView;

@Mapper
public interface RestaurantRepository {

    Restaurant findById(int restaurantId);

    List<Restaurant> findAll();
    List<Restaurant> findAll(Integer categoryId, String query, Integer page, Integer size);

    List<RestaurantView> findAllRestaurantCard();
    List<RestaurantView> findAllRestaurantCard(Integer categoryId, String query);

    List<RestaurantView> getRanking(Integer categoryId);

}
