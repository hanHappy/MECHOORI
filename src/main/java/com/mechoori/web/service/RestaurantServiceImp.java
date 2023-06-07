package com.mechoori.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.repository.RestaurantRepository;

@Service
public class RestaurantServiceImp implements RestaurantService{

	@Autowired
	private RestaurantRepository repository;
	
	@Override
	public List<Restaurant> getList(int categoryId) {
		
		List<Restaurant> list = repository.findAll(categoryId);
		
	return list;
	}

	@Override
	public Restaurant getDetail(int restaurantId) {
		Restaurant restaurant = repository.findById(restaurantId);
		return restaurant;
	}

//	@Override
//	public Restaurant getDetail(int id) throws 식당없음예외 {
//		
//		Restaurant restaurant = repository.getDetail();
//		if(restaurant==null)
//			throw new 식당없음예외();
//		
//		return restaurant;
//	}

}
