package com.mechoori.web.api.controller;

import com.mechoori.web.entity.RateListView;
import com.mechoori.web.entity.ReviewListView;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("apiRateController")
@RequestMapping("/api/rate")
public class RateController {

    @Autowired
    private RateService service;


    @DeleteMapping("{id}")
    public int deleteReview(@PathVariable("id") int id) {

        System.out.println(id);

        return  service.delete(id);
    }


    @GetMapping("{id}/reviews")
    public List<ReviewListView> list(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @PathVariable("id") int restaurantId) {


        List<ReviewListView> list = service.getViewList(restaurantId, offset);
        System.out.println(list);
        return list;
    }


}