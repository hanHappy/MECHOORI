package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.Category;

public interface CategoryService {

    List<Category> getList();

    Category getDetail(int categoryId);

    void save(String[] name, String[] image);

    void delete(int[] id);

    void update(String[] name, String[] image, int[] id);

    
}
