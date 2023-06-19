package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantCardView;

@Mapper
public interface RestaurantRepository {

    List<Restaurant> findAllByCtgId(int categoryId);

    Restaurant findById(int restaurantId);

    List<RestaurantCardView> findAllRestaurantCard();

    List<RestaurantCardView> findAllRestaurantCardByCtgId(int categoryId);

    List<RestaurantCardView> findAllRestaurantCardByQuery(String query);
}
