package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.TopCategory;

@Mapper
public interface CategoryRepository {

    List<TopCategory> findAllTopCategory();

    TopCategory findTopCategoryById(int categoryId);

    void saveTopCategory(TopCategory category);
    
    void updateTopCategory(TopCategory category);
    
    void deleteTopCategory(int[] id);

}