package com.mechoori.web.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantView;
import com.mechoori.web.entity.RestaurantDetail;

public interface RestaurantService {

	Restaurant getDetailById(int restaurantId);
	
    RestaurantDetail getRestaurantDetailById(int restaurantId);

	List<Restaurant> getList();
	List<Restaurant> getListByCtgId(Integer categoryId);
    List<Restaurant> getListByQuery(String query, Integer page, Integer size);
	List<Restaurant> getListByPage(Integer page, Integer size);

	List<RestaurantView> getRestaurantViewList();
	List<RestaurantView> getRestaurantViewListByCtgId(Integer categoryId, String query);
	List<RestaurantView> getRestaurantViewListByQuery(Integer categoryId, String query);

	List<Integer> getPages();

	List<RestaurantView> getRanking(Integer categoryId);
	
}