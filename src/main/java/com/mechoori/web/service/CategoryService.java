package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.Category;
import com.mechoori.web.entity.TopCategory;

public interface CategoryService {

    List<TopCategory> getTopCategoryList();
    TopCategory getTopCategoryDetail(int categoryId);
    void saveTopCategory(TopCategory category);
    void updateTopCategory(TopCategory category);
    void deleteTopCategory(int[] id);

    List<Category> getList();
    List<Category> getMainCategoryList();
    List<Category> getOtherCategoryList();

}
