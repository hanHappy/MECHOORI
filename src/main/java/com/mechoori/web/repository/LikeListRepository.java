package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.LikeList;

@Mapper
public interface LikeListRepository {

    List<LikeList> findAll(int memberId);

}
