package com.mechoori.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.RestaurantCard;
import com.mechoori.web.entity.RestaurantDetail;
import com.mechoori.web.service.MenuService;
import com.mechoori.web.service.RestaurantCardService;
import com.mechoori.web.service.RestaurantDetailService;
import com.mechoori.web.service.RestaurantService;
import com.mechoori.web.service.TopCategoryService;

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
	private TopCategoryService ctgService;

	@Autowired
	private RestaurantDetailService rstnService;

	@GetMapping("/list")
	public String list(
			@RequestParam(name = "q", required = false) String query,
			@RequestParam(name = "c", required = false) Integer ctgId,
			Model model) {

		List<RestaurantCard> list = null;
		// 식당 리스트 출력
		if(query==null&&ctgId==null)
			list = rescService.getList();
		else if (query != null)
			list = rescService.getListByQuery(query);
		else if (ctgId != null)
			list = rescService.getListByCtgId(ctgId);

		model.addAttribute("list", list);

		return "restaurant/list";
	}

	@GetMapping("{id}")
	public String detail(
			@PathVariable("id") int restaurantId,
			Model model) {

		List<Menu> menuList = menuService.getList(restaurantId);
		RestaurantDetail rstnDetail = rstnService.getDetail(restaurantId);

		model.addAttribute("menuList", menuList);
		model.addAttribute("rstnDetail", rstnDetail);

		return "restaurant/detail";
	}

	@GetMapping("{id}/rate")
	public String rate(@PathVariable("id") int restaurantId, Integer menuId,
			Model model) {

		return "restaurant/rate";
	}
}