package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantCardView;
import com.mechoori.web.entity.RestaurantDetailView;

public interface RestaurantService {

	List<Restaurant> getListByCtgId(int categoryId);
	Restaurant getDetailById(int restaurantId);

	List<RestaurantCardView> getRestaurantCardList();
	List<RestaurantCardView> getRestaurantCardListByCtgId(int categoryId);
	List<RestaurantCardView> getRestaurantCardListByQuery(String query);

	RestaurantDetailView getRestaurantDetailById(int restaurantId);

}