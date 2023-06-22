package com.mechoori.web.controller;

import java.util.List;

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
	
	@GetMapping("/")
	public String index(Model model) {

		List<TopCategory> list = service.getTopCategoryList();

		model.addAttribute("list", list);

		return "index";
	}
}