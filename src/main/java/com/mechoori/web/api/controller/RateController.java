package com.mechoori.web.api.controller;

import com.mechoori.web.entity.ReviewListView;
import com.mechoori.web.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/{id}")
    public List<ReviewListView> list(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "ctgId", required = false) Integer categoryId) {

        return service.getViewList(categoryId, offset);
    }

}