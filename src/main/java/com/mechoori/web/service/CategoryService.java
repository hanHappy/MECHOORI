package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.Category;

public interface CategoryService {

    List<Category> getList();

    Category getDetail(int categoryId);

    int save(Category category);

}
