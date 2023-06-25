package com.mechoori.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mechoori.web.entity.Category;
import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantView;
import com.mechoori.web.entity.RestaurantDetail;
import com.mechoori.web.entity.TopCategory;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.CategoryService;
import com.mechoori.web.service.MenuService;
import com.mechoori.web.service.RateService;
import com.mechoori.web.service.RestaurantService;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private RateService rateService;
	
	@GetMapping("/list")
	public String list(
			@RequestParam(name = "q", required = false) String query,
			@RequestParam(name = "c", required = false) Integer ctgId,
			Model model) {

		List<TopCategory> mainCtgList = categoryService.getTopCategoryList();
		List<Category> otherCtgList = categoryService.getOtherCategoryList();
		

		List<RestaurantView> list = null;
		// 식당 리스트 출력
		if(query==null&&ctgId==null)
			list = restaurantService.getRestaurantViewList();
		else if (query != null)
			list = restaurantService.getRestaurantViewListByQuery(ctgId, query);
		else if (ctgId != null)
			list = restaurantService.getRestaurantViewListByCtgId(ctgId, query);

		model.addAttribute("list", list)
			 .addAttribute("mainCtgList", mainCtgList)
			 .addAttribute("otherCtgList", otherCtgList);
			
		return "restaurant/list";
	}

	@GetMapping("{id}")
	public String detail(
			@PathVariable("id") int restaurantId,
			Model model) {

		List<Menu> menuList = menuService.getList(restaurantId);
		RestaurantDetail restaurant = restaurantService.getRestaurantDetailById(restaurantId);


		model.addAttribute("menuList", menuList);
		model.addAttribute("r", restaurant);

		return "restaurant/detail";
	}

	@GetMapping("{id}/rate")
	public String rate(@PathVariable("id") int restaurantId, Model model) {

		Restaurant restaurant = restaurantService.getDetailById(restaurantId);
		List<Menu> menuList = menuService.getList(restaurantId);

		model.addAttribute("menuList", menuList)
			 .addAttribute("r", restaurant);

		return "restaurant/rate";
	}

	@PostMapping("{id}/rate")
	public String rate(
					Rate rate,
					@AuthenticationPrincipal MechooriUserDetails user){
		rateService.add(rate, user.getId());
		// FIXME index -> rate-result로 수정해야 함
		return "redirect:/";
	}

// 	@GetMapping("/ranking")
// 	public String ranking(Model model, String category) {


// //		List<Restaurant> list = restaurantService.getRanking(category);

// 		List<TopCategory> mainCtgList = categoryService.getTopCategoryList();

// 		List<RestaurantView> list = restaurantService.getRestaurantViewList();


// 		model.addAttribute("list",list);
// 		model.addAttribute("ctg",mainCtgList);


// 		return "/restaurant/ranking";
// 	}

	@GetMapping("/mapPage/{id}")
	public String map(
			@PathVariable("id") int restaurantId,
			Model model) {

		Restaurant restaurant = restaurantService.getDetailById(restaurantId);
		RestaurantDetail res = restaurantService.getRestaurantDetailById(restaurantId);



		model.addAttribute("list",restaurant);
		model.addAttribute("r",res);


		return "/mapPage";
	}


}