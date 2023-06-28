package com.mechoori.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.Statistics;
import com.mechoori.web.repository.MenuRepository;
import com.mechoori.web.repository.RateRepository;

@Service
public class RateServiceImp implements RateService{
    
    @Autowired
    private RateRepository repository;
    
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void add(Rate rate, int userId) {
        rate.setId(null);
        rate.setUserId(userId);
        repository.add(rate);
        // 메뉴테이블의 누적 가성비를 가져와서 더해줌?
        int ratePrice = rate.getPrice();

        // 메뉴 테이블의 누적 평가 가격
        Menu menu = menuRepository.findById(rate.getMenuId());
        int cumulativeRatedPrice = menu.getRatedPrice();
        
        int result = (ratePrice + cumulativeRatedPrice)/2;

        menu.setRatedPrice(result);
        menuRepository.update(menu);
    }
    
    // @Override
    // public List<Rate> getListByRestaurantId(int restaurantId) {
    //     // TODO Auto-generated method stub
	// 	return repository.findAll(restaurantId);
    // }

    @Override
    public List<Rate> getListByMenuIds(List<Integer> menuIds) {
        return repository.findByMenuIds(menuIds);
    }

    @Override
    public Map<String, Statistics> getData(int memberId) {
        Map<String, Statistics> data = new HashMap<>();

        return repository.getData(memberId);
    }
}
