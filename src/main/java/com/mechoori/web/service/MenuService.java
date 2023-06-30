package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.Menu;

public interface MenuService {

    List<Menu> getList(int restaurantId);

    Menu getDetail(int menuId);

    String getMenuName(int menuId, List<Menu> menuList);

    void add(Menu menu);
    void update(Menu menu);

}
