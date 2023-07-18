package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.RestaurantRankView;

public interface RestaurantRankService {

    List<RestaurantRankView> getRankTop5();

}
