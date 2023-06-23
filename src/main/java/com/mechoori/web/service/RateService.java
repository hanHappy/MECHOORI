package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.Rate;

public interface RateService {

    void add(Rate rate, int userId);

    // List<Rate> getListByRestaurantId(int restaurantId);
    List<Rate> getListByMenuIds(List<Integer> menuIds);

}
