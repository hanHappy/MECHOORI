package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.Statistics;
import com.mechoori.web.entity.Statistics2;

@Mapper
public interface RateRepository {

    void add(Rate rate);

    // List<Rate> findAll(int restaurantId);

    List<Rate> findByMenuIds(List<Integer> menuIds);

    List<Statistics> findData(int memberId);

    List<Statistics2> findData2(int memberId);
}
