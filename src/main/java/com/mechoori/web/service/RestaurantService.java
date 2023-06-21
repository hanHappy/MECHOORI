package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantCardView;
import com.mechoori.web.entity.RestaurantDetail;

public interface RestaurantService {

	Restaurant getDetailById(int restaurantId);
	
    RestaurantDetail getRestaurantDetailById(int restaurantId);

	List<Restaurant> getList();
	List<Restaurant> getListByCtgId(int categoryId);

	List<RestaurantCardView> getRestaurantCardList();
	List<RestaurantCardView> getRestaurantCardListByCtgId(Integer categoryId, String query);
	List<RestaurantCardView> getRestaurantCardListByQuery(Integer categoryId, String query);


	List<RestaurantCardView> getRanking(Integer categoryId);

}