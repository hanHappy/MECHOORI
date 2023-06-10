package com.mechoori.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Category;
import com.mechoori.web.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> getList() {
        return repository.findAll();
    }

    @Override
    public Category getDetail(int categoryId) {
        return repository.findById(categoryId);
    }

    @Override
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    public void delete(int[] id) {
        repository.delete(id);
    }

    @Override
    public void update(Category category) {
        repository.update(category);
    }

}
