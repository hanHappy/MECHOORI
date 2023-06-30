package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantView;
import com.mechoori.web.entity.RestaurantView;

@Mapper
public interface RestaurantRepository {

    Restaurant findById(int restaurantId);

    List<Restaurant> findAll();
    List<Restaurant> findAll(Integer categoryId, String query, Integer page, Integer size);

    List<RestaurantView> findAllRestaurantView(Integer memberId);
    List<RestaurantView> findAllRestaurantView(Integer memberId, Integer categoryId, String query, String filter);//순서와 필요에 맞게

    List<RestaurantView> getRanking(Integer categoryId);

}
