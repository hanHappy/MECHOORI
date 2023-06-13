package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.RestaurantCard;

public interface RestaurantCardService {

    List<RestaurantCard> getListByCtgId(int categoryId);
    
    List<RestaurantCard> getListByQuery(String query);

    List<RestaurantCard> getList();

}
