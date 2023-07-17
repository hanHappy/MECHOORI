package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.MenuView;

@Mapper
public interface MenuRepository {

	List<Menu> findAll(int restaurantId);

	List<MenuView> findViewAll(int restaurantId);

    Menu findById(int menuId);

    void add(Menu menu);
    void update(Menu menu);

    int delete(int id);
}