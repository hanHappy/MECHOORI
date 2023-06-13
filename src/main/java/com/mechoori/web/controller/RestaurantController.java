package com.mechoori.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantDetail;
import com.mechoori.web.service.CategoryService;
import com.mechoori.web.service.MenuService;
import com.mechoori.web.service.RestaurantCardService;
import com.mechoori.web.service.RestaurantDetailService;
import com.mechoori.web.service.RestaurantService;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService service;
	@Autowired
	private RestaurantCardService rescService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private CategoryService ctgService;
	
	@Autowired
	private RestaurantDetailService rstnService;


	@GetMapping("/list{id}")
	public String list(Model model) {

	
		// 식당 리스트 출력
		// List<RestaurantCard> list = rescService.getList();
		

		// model.addAttribute("list", list);
		
		return "restaurant/category";
	}

	@GetMapping("{id}")
	public String detail(
			@PathVariable("id") int restaurantId,
			Model model) {

		Restaurant restaurant = service.getDetail(restaurantId);
		List<Menu> menuList = menuService.getList(restaurantId);
		RestaurantDetail rstnDetail = rstnService.getDetail(restaurantId);

		model.addAttribute("restaurant", restaurant);
		model.addAttribute("menuList", menuList);
		model.addAttribute("rstnDetail", rstnDetail);

		return "restaurant/detail";
	}

	@GetMapping("{id}/rate")
	public String rate(@PathVariable("id") int restaurantId,Integer menuId,
					   Model model){

		return "restaurant/rate";
	}
}