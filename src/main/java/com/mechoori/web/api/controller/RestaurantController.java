package com.mechoori.web.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mechoori.web.entity.RestaurantView;
import com.mechoori.web.service.MenuService;
import com.mechoori.web.service.RestaurantService;

@RestController("apiRestaurantController")
@RequestMapping("api/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService rstrService;
	@Autowired
	private MenuService menuService;

	@GetMapping("/list")
	public List<RestaurantView> list(
			@RequestParam(name = "q", required = false) String query,
			@RequestParam(name = "c", required = false) Integer ctgId) {

		List<RestaurantView> list = null;
		// 식당 리스트 출력
		if (query == null && ctgId == null)
			list = rstrService.getRestaurantViewList();
		else if (query != null)
			list = rstrService.getRestaurantViewListByQuery(ctgId, query);
		else if (ctgId != null)
			list = rstrService.getRestaurantViewListByCtgId(ctgId, query);

		return list;
	}


	@GetMapping("/ranking")
	public List<RestaurantView> list(
			@RequestParam(name = "ctgId", required = false) Integer categoryId) {


		if (categoryId != null) {
			System.out.println("category");
			return rstrService.getRanking(categoryId);

		}

		return rstrService.getRanking(categoryId);
	}
}
