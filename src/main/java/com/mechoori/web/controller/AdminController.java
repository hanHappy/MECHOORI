package com.mechoori.web.controller;

import java.util.List;

import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.TopCategory;
import com.mechoori.web.service.CategoryService;
import com.mechoori.web.service.MenuService;
import com.mechoori.web.service.RestaurantService;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuService menuService;

    @GetMapping("")
    public String admin() {
        return "/admin";
    }

    // ================= 카테고리 =================
    @GetMapping("category")
    public List<TopCategory> category(Model model) {
        List<TopCategory> list = categoryService.getTopCategoryList();
        model.addAttribute("list", list);
        return list;
    }

    @GetMapping("category/save")
    public String saveTopCategory() {
        return "admin/category/save";
    }

    @PostMapping("category/save")
    public String saveTopCategory(TopCategory category) {
        categoryService.saveTopCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("category/edit")
    public String editTopCategory(int id, Model model) {
        TopCategory category = categoryService.getTopCategoryDetail(id);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @PostMapping("category/edit")
    public String editTopCategory(TopCategory category) {
        categoryService.updateTopCategory(category);
        return "redirect:category";
    }

    @PostMapping("category")
    public String deleteTopCategory(int id){
        return "redirect:";
    }

    // ================= 식당 =================
    @GetMapping("restaurant")
    public String restaurant(
                    @RequestParam(name = "q", required = false) String query,
                    @RequestParam(name = "p", defaultValue = "1") Integer page,
                    @RequestParam(name = "size", defaultValue = "10") Integer size,
                    Model model){
        
        List<Restaurant> list = null;
        if(query != null)
            list = restaurantService.getListByQuery(query, (page*10)-10, size);
        else
            list = restaurantService.getListByPage((page*10)-10, size);

        List<Integer> pages = restaurantService.getPages();

        model.addAttribute("list", list)
             .addAttribute("pages", pages);
        
        return "admin/restaurant";
    }

    @GetMapping("restaurant/add")
    public String addRestaurant(){
        return "admin/restaurant/add";
    }

    @PostMapping("restaurant/add")
    public String addRestaurant(Restaurant restaurant){
        restaurantService.add(restaurant);
        return "redirect:../restaurant";
    }

    @GetMapping("restaurant/{id}/edit")
    public String updateRestaurant(@PathVariable("id") int restaurantId, Model model){
        Restaurant restaurant = restaurantService.getDetailById(restaurantId);
        model.addAttribute("r", restaurant);
        return "admin/restaurant/edit";
    }

    // ================= 메뉴 =================

    @GetMapping("restaurant/{id}/menu")
    public String menu(@PathVariable("id") int restaurantId, Model model){
        
        List<Menu> list = menuService.getList(restaurantId);
        Restaurant restaurant = restaurantService.getDetailById(restaurantId);

        model.addAttribute("list", list)
             .addAttribute("r", restaurant);

        return "/admin/restaurant/menu";
    }

    @GetMapping("restaurant/{rid}/menu/{mid}/edit")
    public String editMenu(Model model, @PathVariable("mid") int menuId, @PathVariable("rid") int restaurantId){
        Menu menu = menuService.getDetail(menuId);
        Restaurant restaurant = restaurantService.getDetailById(restaurantId);
        model.addAttribute("m", menu)
             .addAttribute("r", restaurant);
        return "admin/restaurant/menu/edit";
    }

    @PostMapping("restaurant/{rid}/menu/{mid}/edit")
    public String editMenu(Menu menu, String rid){
        menuService.update(menu);
        return "redirect:/admin/restaurant/"+rid+"/menu";
    }

    @GetMapping("restaurant/{rid}/menu/add")
    public String addMenu(Model model, @PathVariable("rid") int rid){

        model.addAttribute("rid", rid);

        return "admin/restaurant/menu/add";
    }

    @PostMapping("restaurant/{rid}/menu/add")
    public String addMenu(Menu menu, @PathVariable("rid") int rid){
        menuService.add(menu);
        return "redirect:/admin/restaurant/"+rid+"/menu";
    }

}
