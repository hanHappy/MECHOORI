package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Restaurant;

@Mapper
public interface RestaurantRepository {
	
	List<Restaurant> findAll(int categoryId);

	Restaurant findById(int restaurantId);
	
}
