package com.mechoori.web.repository;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.RestaurantLike;

@Mapper
public interface RestaurantLikeRepository {

    int add(RestaurantLike restaurantlike);

    int count(int restaurantId);

    int delete(RestaurantLike restaurantLike);

}
