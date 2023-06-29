package com.mechoori.web.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mechoori.web.entity.RestaurantLike;
import com.mechoori.web.service.RestaurantLikeService;

@RestController
@RequestMapping("api/restaurantlike")
public class RestaurantLikeController {
    
    @Autowired
    private RestaurantLikeService service;

    @PostMapping()
    public int add(@RequestBody RestaurantLike restaurantlike){
        return service.add(restaurantlike);
    }

    @GetMapping("count")
    public int count(@RequestParam("rid") int restaurantId){
        return service.getCount(restaurantId);
    }

    @DeleteMapping("{restaurantId}/members/{memberId}")
    public int delete(@PathVariable("restaurantId") int restaurantId, 
                      @PathVariable("memberId") int memberId){
        RestaurantLike restaurantLike = RestaurantLike.builder()
                                                      .restaurantId(restaurantId)
                                                      .memberId(memberId)
                                                      .build();
        return service.delete(restaurantLike);
    }
    
}
