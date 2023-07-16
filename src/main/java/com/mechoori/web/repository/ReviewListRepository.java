package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.ReviewListView;

@Mapper
public interface ReviewListRepository {

    public List<ReviewListView> findAll(int member);

}
