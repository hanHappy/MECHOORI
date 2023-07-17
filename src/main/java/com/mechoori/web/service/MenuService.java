package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.MenuView;

public interface MenuService {

    List<Menu> getList(int restaurantId);

    List<MenuView> getViewListByRestaurantId(int restaurantId);

    Menu getDetail(int menuId);

    String getMenuName(int menuId, List<MenuView> menuList);

    void add(Menu menu);
    void update(Menu menu);

    int delete(int id);
}
