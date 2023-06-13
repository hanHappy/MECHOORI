package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.TopCategory;

public interface TopCategoryService {

    List<TopCategory> getList();

    TopCategory getDetail(int categoryId);

    void save(TopCategory category);

    void delete(int[] id);

    void update(TopCategory category);

    
}
