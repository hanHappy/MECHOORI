package com.mechoori.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mechoori.web.entity.Category;
import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.service.CategoryService;
import com.mechoori.web.service.RestaurantService;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService service;
	@Autowired
	private CategoryService ctgService;

	@GetMapping("/list/category/{id}")
	public String list(
			@PathVariable("id") int categoryId,
			Model model) {

		List<Restaurant> list = service.getList(categoryId);

		Category category = ctgService.getDetail(categoryId);

		model.addAttribute("list", list)
			.addAttribute("category", category);

		return "restaurant/category";
	}

	@GetMapping("{id}")
	public String detail(
			@PathVariable("id") int restaurantId,
			Model model) {

		Restaurant restaurant = service.getDetail(restaurantId);
		List<Menu> menuList = service.getMenuList(restaurantId);

		model.addAttribute("restaurant", restaurant)
				.addAttribute("menuList", menuList);

		return "restaurant/detail";
	}

	// @GetMapping("/list")
	// public String list(
	// @RequestParam("ctgId") int categoryId,
	// Model model) {
	//
	// List<Restaurant> list = service.getList(categoryId);
	//
	// model.addAttribute("list", list);
	//
	// if()
	// return "html/search-result-by-category";
	// else if()
	// return "html/search-result-by-input";
	// }
}
