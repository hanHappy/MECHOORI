package com.mechoori.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.MenuView;
import com.mechoori.web.repository.MenuRepository;

@Service
public class MenuServiceImp implements MenuService{

    @Autowired
    private MenuRepository repository;

	@Override
	public List<Menu> getList(int restaurantId) {
		return repository.findAll(restaurantId);
	}

	@Override
    public List<MenuView> getViewListByRestaurantId(int restaurantId) {
        return repository.findViewAll(restaurantId);
    }

	@Override
	public Menu getDetail(int menuId) {
		return repository.findById(menuId);
	}

	@Override
	public String getMenuName(int menuId, List<MenuView> menuViewList) {

	    for (MenuView menu : menuViewList) {
			if (menu.getId() == menuId) {
				return menu.getName();
        	}
    	}
    	return "";
    }
}
