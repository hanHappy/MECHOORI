package com.mechoori.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.service.RestaurantService;

@RestController("apiAdminController")
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private RestaurantService restaurantService;

    // @GetMapping("restaurant")
    // public List<Restaurant> list(@RequestParam("q") String query){
    //     List<Restaurant> list = restaurantService.getListByQuery(query);
    //     return list;
    // }
}
