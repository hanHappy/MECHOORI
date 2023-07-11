package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.RateListView;
import com.mechoori.web.entity.Statistics;

@Mapper
public interface RateRepository {

    void add(Rate rate);

    List<Rate> findAll();
    List<Rate> findAll(int menuId);

    List<Rate> findByMenuIds(List<Integer> menuIds);

    List<RateListView> getList(int memberId);

    List<Statistics> findData(int memberId);

    List<RateListView> getList(int memberId, int offset, int size);

    Rate findLatest(int memberId);
}
