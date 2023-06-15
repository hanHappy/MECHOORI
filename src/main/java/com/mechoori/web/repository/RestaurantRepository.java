package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantCard;
import com.mechoori.web.entity.RestaurantDetail;

@Mapper
public interface RestaurantRepository {

    List<Restaurant> findAllByCtgId(int categoryId);

    Restaurant findById(int restaurantId);

    List<RestaurantCard> findAllRestaurantCard();

    List<RestaurantCard> findAllRestaurantCardByCtgId(int categoryId);

    List<RestaurantCard> findAllRestaurantCardByQuery(String query);

    RestaurantDetail findRestaurantDetailById(int restaurantId);


	
}
