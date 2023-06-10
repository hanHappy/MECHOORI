package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Category;

@Mapper
public interface CategoryRepository {

    List<Category> findAll();

    Category findById(int categoryId);

    void save(Category category);

    void delete(int[] id);

    void update(List<Category> list);

}
