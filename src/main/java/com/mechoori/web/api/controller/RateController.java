package com.mechoori.web.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechoori.web.service.RateService;

@RestController("apiRateController")
@RequestMapping("api/rate")
public class RateController {

    @Autowired
    private RateService rateService;
    
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") int id){
        return rateService.delete(id);
    }

}