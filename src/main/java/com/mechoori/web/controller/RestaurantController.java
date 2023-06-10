package com.mechoori.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mechoori.web.entity.Category;
import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.service.CategoryService;
import com.mechoori.web.service.MenuService;
import com.mechoori.web.service.RestaurantService;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService service;
	@Autowired
	private MenuService menuService;
	@Autowired
	private CategoryService ctgService;

	@GetMapping("/list/category/{id}")
	public String listByCategoryId(
			@PathVariable("id") int categoryId,
			Model model) {

		// 헤더에 식당 카테고리 출력
		Category category = ctgService.getDetail(categoryId);
		// 식당 리스트 출력
		List<Restaurant> list = service.getList(categoryId);

		model.addAttribute("list", list)
				.addAttribute("category", category);
		
		return "restaurant/category";
	}

	@GetMapping("/list")
	public String listByQuery(@RequestParam(name = "q", required = false) String query){
		// service.getListByQuery(query);
		return "restaurant/list";
	}

	@GetMapping("{id}")
	public String detail(
			@PathVariable("id") int restaurantId,
			Model model) {

		Restaurant restaurant = service.getDetail(restaurantId);
		List<Menu> menuList = menuService.getList(restaurantId);

		model.addAttribute("restaurant", restaurant)
				.addAttribute("menuList", menuList);

		return "restaurant/detail";
	}

	@GetMapping("{id}/rate")
	public String rate(@PathVariable("id") int restaurantId,Integer menuId,
					   Model model){

		return "restaurant/rate";
	}



}
