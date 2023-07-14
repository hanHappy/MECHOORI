package com.mechoori.web.service;

import java.util.ArrayList;
import java.util.List;

import com.mechoori.web.entity.RestaurantRankView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantDetail;
import com.mechoori.web.entity.RestaurantView;
import com.mechoori.web.repository.MenuRepository;
import com.mechoori.web.repository.RestaurantRepository;

@Service
public class RestaurantServiceImp implements RestaurantService {

	@Autowired
	private RestaurantRepository repository;
	@Autowired
	private MenuRepository menuRepository;
	private int size = 6;

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
	public RestaurantView getViewDetailById(Integer memberId, int restaurantId) {
		return repository.findViewById(memberId, restaurantId);
	}


	@Override
	public List<RestaurantView> getRestaurantViewList(Integer memberId, int offset) {
		return repository.findRestaurantViewAll(memberId, offset, this.size);
	}
	
	@Override
	public List<RestaurantView> getRestaurantViewListByTopCtgIdAndFilter(Integer memberId, Integer topCtgId,
			Integer filterId, int offset) {
		String filter = "";

		switch(filterId){
			case 1:
				filter = "value desc";
				break;
			case 2:
				filter = "avg_price";
				break;
			case 3:
				filter = "avg_price desc";
				break;
			case 4:
				filter = "rate_count desc";
				break;
			case 5:
				filter = "like_count desc";
				break;
		}
		return repository.findRestaurantViewAll(memberId, topCtgId, null, null, filter, offset, this.size);
	}

	@Override
	public List<RestaurantView> getRestaurantViewListByTopCtgId(Integer memberId, Integer topCategoryId, int offset) {
		return repository.findRestaurantViewAll(memberId, topCategoryId, null, null, null, offset, this.size);
	}

	@Override
	public List<RestaurantView> getRestaurantViewListByCtgId(Integer memberId, Integer categoryId, int offset) {
		return repository.findRestaurantViewAll(memberId, null, categoryId, null, null, offset, this.size);
	}

	@Override
	public List<RestaurantView> getRestaurantViewListByQuery(Integer memberId, String query, int offset) {
		return repository.findRestaurantViewAll(memberId, null, null, query, null, offset, this.size);
	}

	@Override
	public List<RestaurantView> getRestaurantViewListByFilter(Integer memberId, Integer ctgId, Integer filterId, int offset) {

		String filter = "";

		switch(filterId){
			case 1:
				filter = "value desc";
				break;
			case 2:
				filter = "avg_price";
				break;
			case 3:
				filter = "avg_price desc";
				break;
			case 4:
				filter = "rate_count desc";
				break;
			case 5:
				filter = "like_count desc";
				break;
		}

		return repository.findRestaurantViewAll(memberId, null, ctgId, null, filter, offset, this.size);
	}




	@Override
	public RestaurantDetail getRestaurantDetailById(int restaurantId) {
		// FIXME RestaurantView로 넘겨주기
		// List<Menu> menuList = menuRepository.findAll(restaurantId);

		// int avgPrice = 0;
		// int avgRatedPrice = 0;

		// for (Menu menu : menuList) {
		// 	avgPrice = (((avgPrice + menu.getPrice()) / 2) / 100) * 100;
		// 	avgRatedPrice = (((avgRatedPrice + menu.getRatedPrice()) / 2) / 100) * 100;
		// }

		// int value = (int) (((double) avgRatedPrice / avgPrice) * 100);

		// Restaurant temp = getDetailById(restaurantId);
		// RestaurantDetail restaurant = new RestaurantDetail();
		// restaurant.setId(temp.getId());
		// restaurant.setName(temp.getName());
		// restaurant.setImg(temp.getImg());
		// restaurant.setIntro(temp.getIntro());
		// restaurant.setAddress(temp.getAddress());
		// restaurant.setOperatingTime(temp.getOperatingTime());
		// restaurant.setContactNumber(temp.getContactNumber());
		// restaurant.setAvgPrice(avgPrice);
		// restaurant.setAvgRatedPrice(avgRatedPrice);
		// restaurant.setValue(value);

		return new RestaurantDetail();
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
	public List<RestaurantRankView> getRanking(Integer categoryId, int offset) {
		return repository.getRanking(categoryId, offset, 6);
	}

//	@Override
//	public List<RestaurantRankView> getRanking(int offset) {
//		return repository.getRanking(offset);
//	}

//	@Override
//	public List<RestaurantView> getRanking(Integer categoryId) {
//		return repository.getRanking(categoryId);
//	}
//
//	@Override
//	public List<RestaurantView> getRanking() {
//		return repository.getRanking();
//	}

	@Override
	public void add(Restaurant restaurant) {
		repository.add(restaurant);
	}


}

// TODO : 예외처리 생각합시다
// @Override
// public Restaurant getDetail(int id) throws 식당없음예외 {
// Restaurant restaurant = repository.getDetail();
// if(restaurant==null)
// throw new 식당없음예외();
//
// return restaurant;
// }