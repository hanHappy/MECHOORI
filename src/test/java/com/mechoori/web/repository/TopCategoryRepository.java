package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.TopCategory;

@Mapper
public interface TopCategoryRepository {

    List<TopCategory> findAll();

    TopCategory findById(int TopCategoryId);

    void save(TopCategory TopCategory);
    
    void update(TopCategory TopCategory);
    
    void delete(int[] id);


}