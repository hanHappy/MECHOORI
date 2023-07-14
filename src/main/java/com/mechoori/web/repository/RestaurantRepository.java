package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantRankView;
import com.mechoori.web.entity.RestaurantView;

@Mapper
public interface RestaurantRepository {

    Restaurant findById(int restaurantId);
    void add(Restaurant restaurant);
    
    List<Restaurant> findAll();
    List<Restaurant> findAllRestaurant();
    List<Restaurant> findAll(Integer categoryId, String query, Integer page, Integer size);
    
    RestaurantView findViewById(Integer memberId, int restaurantId);
    List<RestaurantView> findRestaurantViewAll(Integer memberId, int offset, int size);
    List<RestaurantView> findRestaurantViewAll(Integer memberId, Integer topCategoryId, Integer categoryId, String query, String filter, int offset, int size);

    List<RestaurantRankView> getRanking(Integer categoryId, int offset, int size);
    List<RestaurantRankView> findRankAll(Integer size, Integer categoryId);

}
