package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.Statistics;
import com.mechoori.web.entity.Statistics2;
import com.mechoori.web.entity.Statistics3;

@Mapper
public interface RateRepository {

    void add(Rate rate);

    List<Rate> findAll();
    List<Rate> findAll(int menuId);

    List<Rate> findByMenuIds(List<Integer> menuIds);

    List<Rate> getList(int memberId);


    // ------ chart ------
    List<Statistics> findData(int memberId);

    List<Statistics2> findData2(int memberId);

    List<Statistics3> findData3(int memberId);

    Rate findLatest(int memberId);

    
}
