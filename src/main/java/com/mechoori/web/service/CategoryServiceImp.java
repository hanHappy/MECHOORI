package com.mechoori.web.service;

import java.util.ArrayList;
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
    public void update(String[] name, String[] image, int[] id) {
        List<Category> list = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            Category category = Category.builder()
                                        .name(name[i])
                                        .image(image[i])
                                        .id(id[i])
                                        .build();
            list.add(category);
            System.out.println(category);
        }
        // repository.update(list);
    }

}
