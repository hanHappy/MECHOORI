package com.mechoori.web.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantCard;
import com.mechoori.web.entity.RestaurantDetail;

public interface RestaurantService {

	Restaurant getDetailById(int restaurantId);
	
    RestaurantDetail getRestaurantDetailById(int restaurantId);

	List<Restaurant> getList();
	List<Restaurant> getListByCtgId(Integer categoryId);
    List<Restaurant> getListByQuery(String query, Integer page, Integer size);
	List<Restaurant> getListByPage(Integer page, Integer size);

	List<RestaurantCard> getRestaurantCardList();
	List<RestaurantCard> getRestaurantCardListByCtgId(Integer categoryId, String query);
	List<RestaurantCard> getRestaurantCardListByQuery(Integer categoryId, String query);

	List<Integer> getPages();

	List<RestaurantCard> getRanking(Integer categoryId);

	List<Restaurant> findAllRestaurant();

	
}