package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.RestaurantCard;

@Mapper
public interface RestaurantCardRepository {

    List<RestaurantCard> findAll(int categoryId);

}
