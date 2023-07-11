package com.mechoori.web.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
import com.mechoori.web.entity.MenuView;
import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantDetail;
import com.mechoori.web.entity.RestaurantView;
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
            @AuthenticationPrincipal MechooriUserDetails member,
            Model model) {

        List<TopCategory> mainCtgList = categoryService.getTopCategoryList();
        List<Category> otherCtgList = categoryService.getOtherCategoryList();

        List<RestaurantView> list = null;

        Integer memberId = null;

        if (member != null)
            memberId = member.getId();

        // 식당 리스트 출력
        if (query == null && ctgId == null)
            list = restaurantService.getRestaurantViewList(memberId);
        else if (query != null)
            list = restaurantService.getRestaurantViewListByQuery(memberId, query);
        else if (ctgId != null)
            list = restaurantService.getRestaurantViewListByCtgId(memberId, ctgId);

        model.addAttribute("list", list)
                .addAttribute("mainCtgList", mainCtgList)
                .addAttribute("otherCtgList", otherCtgList);

        return "restaurant/list";
    }

    @GetMapping("{id}")
    public String detail(
            @PathVariable("id") int restaurantId,
            Model model) {

        // List<Menu> menuList = menuService.getList(restaurantId);
        RestaurantView restaurantView = restaurantService.getViewDetailById(restaurantId);
        List<MenuView> menuViewList = menuService.getViewListByRestaurantId(restaurantId);

        //아이디
        List<Integer> menuIds = new ArrayList<>();
        for (MenuView menuView : menuViewList) {
            menuIds.add(menuView.getId());
        }
        //리뷰
        List<Rate> rateList = rateService.getListByMenuIds(menuIds);

        //리뷰 최신순 4개
        List<Rate> top4Rates;
        if (rateList.size() < 4) {
            List<Rate> sortedList = new ArrayList<>(rateList);
            sortedList.sort(Comparator.comparing(Rate::getRegDate).reversed());
            top4Rates = sortedList.subList(0, rateList.size());
        } else {
            List<Rate> sortedList = new ArrayList<>(rateList);
            sortedList.sort(Comparator.comparing(Rate::getRegDate).reversed());
            top4Rates = sortedList.subList(0, 4);
        }

        List<String> menuNames = new ArrayList<>();
        for (Rate rate : top4Rates) {
            int menuId = rate.getMenuId();
            String menuName = menuService.getMenuName(menuId, menuViewList);
            menuNames.add(menuName);
        }

        model.addAttribute("menuViewList", menuViewList);
        model.addAttribute("r", restaurantView);
        model.addAttribute("rateList", rateList);
        model.addAttribute("menuNames", menuNames);
        model.addAttribute("top4Rates", top4Rates);

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
            @AuthenticationPrincipal MechooriUserDetails user) {
        rateService.add(rate, user.getId());
        // FIXME index -> rate-result로 수정해야 함//
        return "redirect:rate-result";
    }

    @GetMapping("{id}/rate-result")
    public String rateResult(
        @PathVariable("id") int restaurantId,
        Model model,
        @AuthenticationPrincipal MechooriUserDetails member){

        // 임프에서 받은 맵을 모델에 담아서 뷰에 넘겨준다
        // 방금평가한 데이터가 필요하기 때문에 rate테이블이 필요하고 
        // 레코드를 가져오기 위해 member(id)를 넘겨준다

        Map<String, Object> result =  rateService.getRateResult(restaurantId, member.getId());
        model.addAttribute("result", result);

        return "/restaurnat/rate-result";

    }

//get mapping
//model -> map<>  =
// data 


    @GetMapping("/ranking")
    public String ranking(Model model, Integer categoryId) {

       List<TopCategory> mainCtgList = categoryService.getTopCategoryList();

       List<RestaurantView> list = restaurantService.getRanking();


        System.out.println(mainCtgList);
        System.out.println(list);
       model.addAttribute("list",list);
       model.addAttribute("ctg",mainCtgList);

       return "/restaurant/ranking";
    }

    @GetMapping("/mapPage/{id}")
    public String map(
            @PathVariable("id") int restaurantId,
            Model model) {

        Restaurant restaurant = restaurantService.getDetailById(restaurantId);
        RestaurantDetail res = restaurantService.getRestaurantDetailById(restaurantId);

        model.addAttribute("list", restaurant);
        model.addAttribute("r", res);

        return "restaurant/mapPage";
    }
//ep

}