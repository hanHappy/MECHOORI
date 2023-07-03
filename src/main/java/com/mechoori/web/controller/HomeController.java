package com.mechoori.web.controller;

import java.security.Principal;
import java.util.List;

import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mechoori.web.entity.TopCategory;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.CategoryService;

@Controller
public class HomeController {
	
	@Autowired
	private	CategoryService service;
	@Autowired
	private RestaurantService restaurantService;


	
	@GetMapping("/")
	public String index(
			Model model
			// @AuthenticationPrincipal MechooriUserDetails member //
	) {

		List<TopCategory> list = service.getTopCategoryList();

		// String nickname = member.getNickname();//

		model.addAttribute("list", list);
			// .addAttribute("nickname", nickname);//

		// for(int i=0; i<10; i++){
		// 	System.out.println(nickname);
		// }

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