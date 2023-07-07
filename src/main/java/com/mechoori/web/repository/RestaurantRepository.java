package com.mechoori.web.repository;

import java.util.List;

import com.mechoori.web.entity.RestaurantRankView;
import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantView;
import com.mechoori.web.entity.RestaurantView;

@Mapper
public interface RestaurantRepository {

    Restaurant findById(int restaurantId);
    RestaurantView findViewById(Integer memberId, int restaurantId);

    List<Restaurant> findAll();
    List<Restaurant> findAll(Integer categoryId, String query, Integer page, Integer size);

    List<RestaurantView> findAllRestaurantView(Integer memberId);
    List<RestaurantView> findAllRestaurantView(Integer memberId, Integer topCategoryId, Integer categoryId, String query, String filter);

    List<RestaurantRankView> getRanking(Integer categoryId, int offset, int size);


    void add(Restaurant restaurant);

    List<Restaurant> findAllRestaurant();

//    List<RestaurantView> getRanking(Integer categoryId);
//    List<RestaurantRankView> getRanking(int offset);
}
