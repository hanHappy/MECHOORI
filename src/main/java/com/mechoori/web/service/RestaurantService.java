package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.*;

public interface RestaurantService {

	Restaurant getDetailById(int restaurantId);
	RestaurantView getViewDetailById(Integer memberId, int restaurantId);
	
    RestaurantDetail getRestaurantDetailById(int restaurantId);

	List<Restaurant> getList();
	List<Restaurant> getListByCtgId(Integer categoryId);
    List<Restaurant> getListByQuery(String query, Integer page, Integer size);
	List<Restaurant> getListByPage(Integer page, Integer size);

	List<RestaurantView> getRestaurantViewList(Integer memberId, int offset);
    List<RestaurantView> getRestaurantViewListByTopCtgIdAndFilter(Integer memberId, Integer topCtgId, Integer filterId, int offset);
    List<RestaurantView> getRestaurantViewListByTopCtgId(Integer memberId, Integer topCategoryId, int offset);
	List<RestaurantView> getRestaurantViewListByCtgId(Integer memberId, Integer categoryId, int offset);
	List<RestaurantView> getRestaurantViewListByQuery(Integer memberId, String query, int offset);
    List<RestaurantView> getRestaurantViewListByFilter(Integer memberId, Integer ctgId, Integer filterId, int offset);

	List<Integer> getPages();
	
	List<RestaurantRankView> getRanking(Integer categoryId , int offset);
    void add(Restaurant restaurant);
    void update(Restaurant restaurant);

	List<Review> findReviewAll(int restaurantId);
	


//	List<RestaurantRankView> getRanking(int offset);

//	List<RestaurantView> getRanking(Integer categoryId);
}