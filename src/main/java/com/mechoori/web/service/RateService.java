package com.mechoori.web.service;

import java.util.List;
import java.util.Map;

import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.RateListView;
import com.mechoori.web.entity.ReviewListView;
import com.mechoori.web.entity.Statistics2;
import com.mechoori.web.entity.Statistics3;

public interface RateService {

    void add(Rate rate, int memberId);

    // List<Rate> getListByRestaurantId(int restaurantId);
    List<Rate> getListByMenuIds(List<Integer> menuIds);

    // List<Rate> getRatedPrice(Rate rate, Integer memberId);

    // ----- chart -----
    Map<String, Integer> getDate(int memberId);
    List<Statistics2> getDate2(int memberId);
    List<Statistics3> getDate3(int memberId);


    List<RateListView> getMyList(int id, int offset);


    Map<String, Object> getRateResult(int restaurantId, int memberId);

    List<ReviewListView> getViewList(Integer restaurantId,  int offset);

    int delete(int id);
}
