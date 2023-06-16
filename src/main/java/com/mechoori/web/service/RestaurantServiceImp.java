package com.mechoori.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantCardView;
import com.mechoori.web.entity.RestaurantDetailView;
import com.mechoori.web.repository.RestaurantRepository;

@Service
public class RestaurantServiceImp implements RestaurantService{

	@Autowired
	private RestaurantRepository repository;

	@Override
	public List<Restaurant> getListByCtgId(int categoryId) {
		return repository.findAllByCtgId(categoryId);
	}

	@Override
	public Restaurant getDetailById(int restaurantId) {
		return repository.findById(restaurantId);
	}

	@Override
	public List<RestaurantCardView> getRestaurantCardList() {
		return repository.findAllRestaurantCard();
	}

	@Override
	public List<RestaurantCardView> getRestaurantCardListByCtgId(int categoryId) {
		return repository.findAllRestaurantCardByCtgId(categoryId);
	}

	@Override
	public List<RestaurantCardView> getRestaurantCardListByQuery(String query) {
		return repository.findAllRestaurantCardByQuery(query);
	}

	@Override
	public RestaurantDetailView getRestaurantDetailById(int restaurantId) {
		return repository.findRestaurantDetailById(restaurantId);
	}
	
    
	}


	// TODO : 예외처리 생각합시다
//	@Override
//	public Restaurant getDetail(int id) throws 식당없음예외 {
//		
//		Restaurant restaurant = repository.getDetail();
//		if(restaurant==null)
//			throw new 식당없음예외();
//		
//		return restaurant;
//	}