package com.mechoori.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mechoori.web.entity.RateView;

@Service
public interface RateViewService {
    List<RateView> getRatePriceList(Integer memberId, Integer restaurantId);
    String GetRateAverageUserPrice(Integer restaurantId, Integer ratedAveragePrice);
    String GetRateAverageMyPrice(Integer restaurantId, Integer ratedAveragePrice);
}
