package com.mechoori.web.service;

import java.util.List;
import java.util.Map;

import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.RateListView;

public interface RateService {

    void add(Rate rate, int memberId);

    // List<Rate> getListByRestaurantId(int restaurantId);
    List<Rate> getListByMenuIds(List<Integer> menuIds);

    // List<Rate> getRatedPrice(Rate rate, Integer memberId);

    Map<String, Integer> getDate(int memberId);

    List<RateListView> getList(int id, int offset);

    Map<String, Object> getRateResult(int restaurantId, int memberId);
}
