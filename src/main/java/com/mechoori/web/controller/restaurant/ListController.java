package com.mechoori.web.controller.restaurant;

import java.util.List;

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
public class ListController {
	
	@Autowired
	private RestaurantService service;
	
	@GetMapping("/list/category/{id}")
	public String list(
					@PathVariable("id") int categoryId,
					Model model) {
		
		List<Restaurant> list = service.getList(categoryId);
		
		model.addAttribute("list", list);
		
		return "restaurant/category";
	}
	
//	@GetMapping("/list")
//	public String list(
//			@RequestParam("ctgId") int categoryId,
//			Model model) {
//		
//		List<Restaurant> list = service.getList(categoryId);
//		
//		model.addAttribute("list", list);
//		
//		if()
//			return "html/search-result-by-category";
//		else if()
//			return "html/search-result-by-input";
//	}
}
