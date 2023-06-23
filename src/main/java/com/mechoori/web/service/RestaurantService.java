package com.mechoori.web.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantCardView;
import com.mechoori.web.entity.RestaurantDetail;

public interface
RestaurantService {

	Restaurant getDetailById(int restaurantId);
	
    RestaurantDetail getRestaurantDetailById(int restaurantId);

	List<Restaurant> getList();
	List<Restaurant> getListByCtgId(Integer categoryId);
    List<Restaurant> getListByQuery(String query, Integer page, Integer size);
	List<Restaurant> getListByPage(Integer page, Integer size);

	List<RestaurantCardView> getRestaurantCardList();
	List<RestaurantCardView> getRestaurantCardListByCtgId(Integer categoryId, String query);
	List<RestaurantCardView> getRestaurantCardListByQuery(Integer categoryId, String query);

	List<Integer> getPages();

	List<RestaurantCardView> getRanking(Integer categoryId);
	
}