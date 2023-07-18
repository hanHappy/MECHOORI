package com.mechoori.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Category;
import com.mechoori.web.entity.TopCategory;
import com.mechoori.web.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<TopCategory> getTopCategoryList() {
        return repository.findAllTopCategory();
    }
    @Override
    public TopCategory getTopCategoryDetail(int categoryId) {
        return repository.findTopCategoryById(categoryId);
    }
    @Override
    public void saveTopCategory(TopCategory category) {
        repository.saveTopCategory(category);
    }
    @Override
    public void updateTopCategory(TopCategory category) {
        repository.updateTopCategory(category);
    }
    @Override
    public void deleteTopCategory(int[] id) {
        repository.deleteTopCategory(id);
    }

    @Override
    public List<Category> getList() {
        return repository.findAll();
    }
    @Override
    public List<Category> getMainCategoryList() {
        return repository.findAllMainCategory();
    }
    @Override
    public List<Category> getOtherCategoryList() {
        return repository.findAllOtherCategory();
    }
}