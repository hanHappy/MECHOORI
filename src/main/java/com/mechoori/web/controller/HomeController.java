package com.mechoori.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mechoori.web.entity.RestaurantRankView;
import com.mechoori.web.entity.RestaurantView;
import com.mechoori.web.entity.TopCategory;
import com.mechoori.web.service.CategoryService;
import com.mechoori.web.service.RestaurantRankService;
import com.mechoori.web.service.RestaurantService;

@Controller
public class HomeController {
	
	@Autowired
	private	CategoryService service;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private RestaurantRankService restaurantRankService;

	@GetMapping("/")
	public String index(
			Model model
			// @AuthenticationPrincipal MechooriUserDetails member //
	) {
		List<TopCategory> list = service.getTopCategoryList();
		List<RestaurantRankView> listRank = restaurantRankService.getRankTop5();

		model.addAttribute("list", list);
		model.addAttribute("listRank", listRank);
		
		System.out.println("id----------------------------------: ");
		System.out.println(listRank.get(0).getId());
		
		// for(int i=0; i<10; i++){
		// 	System.out.println(nickname);
		// }

		return "index";
	}



	@GetMapping("map")
	public String map(@RequestParam(name = "q", required = false) String query,
					  @RequestParam(name = "c", required = false) Integer ctgId,
					  Model model) {


		List<RestaurantView> list = null;

		Integer memberId = null;

		return "map";
	}

	}