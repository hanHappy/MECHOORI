package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.RateListView;
import com.mechoori.web.entity.ReviewListView;
import com.mechoori.web.entity.Statistics;
import com.mechoori.web.entity.Statistics2;
import com.mechoori.web.entity.Statistics3;

@Mapper
public interface RateRepository {

    void add(Rate rate);

    List<Rate> findAll();
    List<Rate> findAll(int menuId);

    List<Rate> findByMenuIds(List<Integer> menuIds);

    List<RateListView> getMyList(int memberId);

    // ------ chart ------
    List<Statistics> findData(int memberId);
    List<Statistics2> findData2(int memberId);
    List<Statistics3> findData3(int memberId);

    List<RateListView> getMyList(int memberId, int offset, int size);

    Rate findLatest(int memberId);

    List<ReviewListView> findViewAll(Integer restaurantId, int offset, int size);

    int delete(int id);

    int findCount(int restaurantId);


}
