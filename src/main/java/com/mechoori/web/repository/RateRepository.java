package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Rate;

@Mapper
public interface RateRepository {

    void add(Rate rate);

    // List<Rate> findAll(int restaurantId);

    List<Rate> findByMenuIds(List<Integer> menuIds);
}
