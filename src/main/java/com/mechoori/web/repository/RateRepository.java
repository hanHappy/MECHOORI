package com.mechoori.web.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.Statistics;

@Mapper
public interface RateRepository {

    void add(Rate rate);

    // List<Rate> findAll(int restaurantId);

    List<Rate> findByMenuIds(List<Integer> menuIds);

    Map<String, Statistics> getData(int memberId);
}
