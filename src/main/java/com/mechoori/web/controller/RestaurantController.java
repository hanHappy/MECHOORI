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
import com.mechoori.web.entity.RestaurantCardView;
import com.mechoori.web.entity.RestaurantDetailView;
import com.mechoori.web.service.MenuService;
import com.mechoori.web.service.RestaurantService;
import com.mechoori.web.service.CategoryService;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService rstrService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private CategoryService ctgService;

	@GetMapping("/list")
	public String list(
			@RequestParam(name = "q", required = false) String query,
			@RequestParam(name = "c", required = false) Integer ctgId,
			Model model) {

		List<RestaurantCardView> list = null;
		// 식당 리스트 출력
		if(query==null&&ctgId==null)
			list = rstrService.getRestaurantCardList();
		else if (query != null)
			list = rstrService.getRestaurantCardListByQuery(query);
		else if (ctgId != null)
			list = rstrService.getRestaurantCardListByCtgId(ctgId);

		model.addAttribute("list", list);

		return "restaurant/list";
	}

	@GetMapping("{id}")
	public String detail(
			@PathVariable("id") int restaurantId,
			Model model) {

		List<Menu> menuList = menuService.getList(restaurantId);
		RestaurantDetailView rstnDetail = rstrService.getRestaurantDetailById(restaurantId);

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