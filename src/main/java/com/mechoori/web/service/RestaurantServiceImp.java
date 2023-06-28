package com.mechoori.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantCard;
import com.mechoori.web.entity.RestaurantDetail;
import com.mechoori.web.repository.MenuRepository;
import com.mechoori.web.repository.RestaurantRepository;

@Service
public class RestaurantServiceImp implements RestaurantService {

	@Autowired
	private RestaurantRepository repository;
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public List<Restaurant> getList() {
		return repository.findAll();
	}

	@Override
	public List<Restaurant> getListByCtgId(Integer categoryId) {
		return repository.findAll(categoryId, null, null, null);
	}

	@Override
	public List<Restaurant> getListByPage(Integer page, Integer size) {
		return repository.findAll(null, null, page, size);
	}

	@Override
	public List<Restaurant> getListByQuery(String query, Integer page, Integer size) {
		return repository.findAll(null, query, page, size);
	}

	@Override
	public Restaurant getDetailById(int restaurantId) {
		return repository.findById(restaurantId);
	}

	@Override
	public List<RestaurantCard> getRestaurantCardList(Integer memberId) {
		return repository.findAllRestaurantCard(memberId);
	}

	@Override
	public List<RestaurantCard> getRestaurantCardListByCtgId(Integer memberId, Integer categoryId, String query) {
		return repository.findAllRestaurantCard(memberId, categoryId, null);
	}

	@Override
	public List<RestaurantCard> getRestaurantCardListByQuery(Integer memberId, Integer categoryId, String query) {
		return repository.findAllRestaurantCard(memberId, null, query);
	}

	@Override
	public RestaurantDetail getRestaurantDetailById(int restaurantId) {
		List<Menu> menuList = menuRepository.findAll(restaurantId);

		int avgPrice = 0;
		int avgRatedPrice = 0;

		for (Menu menu : menuList) {
			avgPrice = (((avgPrice + menu.getPrice()) / 2) / 100) * 100;
			avgRatedPrice = (((avgRatedPrice + menu.getRatedPrice()) / 2) / 100) * 100;
		}

		int value = (int) (((double) avgRatedPrice / avgPrice) * 100);

		Restaurant temp = getDetailById(restaurantId);
		RestaurantDetail restaurant = new RestaurantDetail();
		// TODO 줄여쓸 수 있는 방법 알아보자
		restaurant.setId(temp.getId());
		restaurant.setImg(temp.getImg());
		restaurant.setName(temp.getName());
		restaurant.setIntro(temp.getIntro());
		restaurant.setAddress(temp.getAddress());
		restaurant.setOperatingTime(temp.getOperatingTime());
		restaurant.setContactNumber(temp.getContactNumber());
		restaurant.setLikedCount(temp.getLikedCount());
		restaurant.setRatedCount(temp.getRatedCount());
		restaurant.setAvgPrice(avgPrice);
		restaurant.setAvgRatedPrice(avgRatedPrice);
		restaurant.setValue(value);

		return restaurant;
	}

	@Override
	public List<Integer> getPages() {
		List<Restaurant> list = repository.findAll();
		int restaurantNums = (int) Math.ceil((double) list.size() / 10);
		List<Integer> pages = new ArrayList<>();
		for (int i = 0; i < restaurantNums; i++)
			pages.add(i + 1);
		return pages;
	}

	@Override
	public List<RestaurantCard> getRanking(Integer categoryId) {
		return repository.getRanking(categoryId);
	}

	@Override
	public List<Restaurant> findAllRestaurant() {
		return repository.findAllRestaurant();
	}
}

// TODO : 예외처리 생각합시다
// @Override
// public Restaurant getDetail(int id) throws 식당없음예외 {
//
// Restaurant restaurant = repository.getDetail();
// if(restaurant==null)
// throw new 식당없음예외();
//
// return restaurant;
// }