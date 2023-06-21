package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantCardView;

@Mapper
public interface RestaurantRepository {

    Restaurant findById(int restaurantId);

    List<Restaurant> findAll();
    List<Restaurant> findAll(int categoryId);

    List<RestaurantCardView> findAllRestaurantCard();
    List<RestaurantCardView> findAllRestaurantCard(Integer categoryId, String query);

    List<Restaurant> getRanking();

}
