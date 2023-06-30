package com.mechoori.web.controller;

import java.util.List;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mechoori.web.entity.TopCategory;
import com.mechoori.web.service.CategoryService;

@Controller
public class HomeController {
	
	@Autowired
	private	CategoryService service;
	@Autowired
	private RestaurantService restaurantService;


	
	@GetMapping("/")
	public String index(Model model) {

		List<TopCategory> list = service.getTopCategoryList();

		model.addAttribute("list", list);

		return "index";
	}



	@GetMapping("map")
	public String map(Model model){

		List<Restaurant> address = restaurantService.findAllRestaurant();

		model.addAttribute("address",address);

		System.out.println(address);

		return "map";
	}

}