package com.mechoori.web.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechoori.web.service.RateService;
import com.mechoori.web.service.ReviewListService;

@RestController("apiRateController")
@RequestMapping("api/rate")
public class RateController {
    
    @Autowired
    private RateService service;


    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") int id){
        System.out.println(id);
         
        return service.delete(id);
    }
}