package com.mechoori.web.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mechoori.web.entity.LikeList;
import com.mechoori.web.entity.RestaurantLike;
import com.mechoori.web.entity.Statistics2;
import com.mechoori.web.entity.Statistics3;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.RateService;
import com.mechoori.web.service.RestaurantLikeService;

@RestController("apiRateController")
@RequestMapping("api/user/")
public class UserController {

    @Autowired
    private RateService rateService;

    @Autowired
    private RestaurantLikeService restaurantLikeService;

    @GetMapping("my-page/statistics")
    public Map<String, Integer> statistics(
            @AuthenticationPrincipal MechooriUserDetails member) {

        System.out.println("member: " + member);
        Map<String, Integer> data = rateService.getDate(member.getId());
        // System.out.println("data : " + data);

        return data;
    }

    @GetMapping("my-page/statistics2")
    public List<Statistics2> statistics2(
            @AuthenticationPrincipal MechooriUserDetails member) {

        List<Statistics2> data = rateService.getDate2(member.getId());
        // System.out.println("data : " + data);

        return data;
    }

    @GetMapping("my-page/statistics3")
    public List<Statistics3> statistics3(
            @AuthenticationPrincipal MechooriUserDetails member) {

            List<Statistics3> data = rateService.getDate3(member.getId());
        // System.out.println("data : " + data);

        return data;
    }

    @DeleteMapping("my-page/like-list")
    public int delete(RestaurantLike restaurantLike){
        System.out.println(restaurantLike.getRestaurantId());
        System.out.println(restaurantLike.getMemberId());
            restaurantLikeService.delete(restaurantLike);
        return 1;
    } 

}