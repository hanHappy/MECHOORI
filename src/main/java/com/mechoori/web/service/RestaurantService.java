package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantCard;
import com.mechoori.web.entity.RestaurantDetail;

public interface RestaurantService {

	List<Restaurant> getListByCtgId(int categoryId);
	Restaurant getDetailById(int restaurantId);

	List<RestaurantCard> getRestaurantCardList();
	List<RestaurantCard> getRestaurantCardListByCtgId(int categoryId);
	List<RestaurantCard> getRestaurantCardListByQuery(String query);

	RestaurantDetail getRestaurantDetailById(int restaurantId);

}