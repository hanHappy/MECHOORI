package com.mechoori.web.service;

import com.mechoori.web.entity.RestaurantLike;

public interface RestaurantLikeService {

    int add(RestaurantLike restaurantlike);

    int getCount(int restaurantId);

    int delete(RestaurantLike restaurantLike);

}
