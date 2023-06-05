package com.mechoori.web.controller.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			@RequestParam int id) {
		
		Restaurant restaurant = service.getDetail();
		
		return "html/restaurant-detail";
	}
	
	
}
