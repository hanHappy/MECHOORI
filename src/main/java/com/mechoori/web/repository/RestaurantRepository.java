package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantCard;

@Mapper
public interface RestaurantRepository {

    Restaurant findById(int restaurantId);

    List<Restaurant> findAll();
    List<Restaurant> findAll(Integer categoryId, String query, Integer page, Integer size);

    List<RestaurantCard> findAllRestaurantCard();
    List<RestaurantCard> findAllRestaurantCard(Integer categoryId, String query);

    List<RestaurantCard> getRanking(Integer categoryId);


    List<Restaurant> findAllRestaurant();

}
