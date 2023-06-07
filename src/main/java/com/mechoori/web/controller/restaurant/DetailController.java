package com.mechoori.web.controller.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.service.RestaurantService;

@Controller
@RequestMapping("/restaurant")
public class DetailController {
	
	@Autowired
	private RestaurantService service;
	
	// /restaurant/133
	@GetMapping("{id}")
	public String detail(
			@PathVariable("id") int restaurantId,
			Model model) {
		
		Restaurant restaurant = service.getDetail(restaurantId);
		
		model.addAttribute("restaurant", restaurant);
		
		return "restaurant/detail";
	}
}
