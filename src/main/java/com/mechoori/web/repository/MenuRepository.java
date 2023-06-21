package com.mechoori.web.repository;

import java.util.List;

import com.mechoori.web.entity.Restaurant;
import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.Menu;

@Mapper
public interface MenuRepository {

	List<Menu> findAll(int restaurantId);

    Menu findById(int menuId);

    void update(Menu menu);



}