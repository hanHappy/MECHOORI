package com.mechoori.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mechoori.web.entity.RateView;
import com.mechoori.web.repository.RateViewRepository;

public class RateViewServiceImp implements RateViewService{

    @Autowired
    private RateViewRepository rateViewRepository;

    @Override
    public List<RateView> getRatePriceList(Integer memberId, Integer restaurantId) {
            
    }

    @Override
    public String GetRateAverageUserPrice(Integer restaurantId, Integer ratedAveragePrice) {
        if(restaurantId.contains(memberId){
            
    }

    }
    
    @Override
    public String GetRateAverageMyPrice(Integer restaurantId, Integer ratedAveragePrice) {
        if(restaurantId.contains(memberId){

    }

}
