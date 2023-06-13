package com.mechoori.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.TopCategory;
import com.mechoori.web.repository.TopCategoryRepository;

@Service
public class TopCategoryServiceImp implements TopCategoryService {

    @Autowired
    private TopCategoryRepository repository;

    @Override
    public List<TopCategory> getList() {
        return repository.findAll();
    }

    @Override
    public TopCategory getDetail(int categoryId) {
        return repository.findById(categoryId);
    }

    @Override
    public void save(TopCategory category) {
        repository.save(category);
    }

    @Override
    public void delete(int[] id) {
        repository.delete(id);
    }

    @Override
    public void update(TopCategory category) {
        repository.update(category);
    }

}