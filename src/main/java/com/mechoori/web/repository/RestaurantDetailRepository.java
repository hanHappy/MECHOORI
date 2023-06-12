package com.mechoori.web.repository;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.RestaurantDetail;

@Mapper
public interface RestaurantDetailRepository {

    RestaurantDetail findById(int restaurantId);

}
