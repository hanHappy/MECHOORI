package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.Restaurant;

public interface RestaurantService {
	
	List<Restaurant> getList(int categoryId);

	Restaurant getDetail(int restaurantId);

	List<Menu> getMenuList(int restaurantId);



}