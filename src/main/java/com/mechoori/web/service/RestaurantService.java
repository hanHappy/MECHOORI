package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantDetail;
import com.mechoori.web.entity.RestaurantView;
import com.mechoori.web.entity.RestaurantView;

public interface RestaurantService {

	Restaurant getDetailById(int restaurantId);
	
    RestaurantDetail getRestaurantDetailById(int restaurantId);

	List<Restaurant> getList();
	List<Restaurant> getListByCtgId(Integer categoryId);
    List<Restaurant> getListByQuery(String query, Integer page, Integer size);
	List<Restaurant> getListByPage(Integer page, Integer size);

	List<RestaurantView> getRestaurantViewList(Integer memberId);
    List<RestaurantView> getRestaurantViewListByTopCtgId(Integer memberId, Integer topCategoryId);
	List<RestaurantView> getRestaurantViewListByCtgId(Integer memberId, Integer categoryId);
	List<RestaurantView> getRestaurantViewListByQuery(Integer memberId, String query);
    List<RestaurantView> getRestaurantViewListByFilter(Integer memberId, Integer ctgId, Integer filterId);

	List<Integer> getPages();
	
	List<RestaurantView> getRanking(Integer categoryId);
	List<RestaurantView> getRanking();
    void add(Restaurant restaurant);

	List<Restaurant> findAllRestaurant();


}