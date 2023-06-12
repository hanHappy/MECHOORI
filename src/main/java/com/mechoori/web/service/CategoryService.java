package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.Category;

public interface CategoryService {

    List<Category> getList();

    Category getDetail(int categoryId);

    void save(Category category);

    void delete(int[] id);

    void update(Category category);

    
}
