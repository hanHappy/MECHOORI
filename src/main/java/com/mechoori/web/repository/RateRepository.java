package com.mechoori.web.repository;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Rate;

@Mapper
public interface RateRepository {

    void add(Rate rate);

}
