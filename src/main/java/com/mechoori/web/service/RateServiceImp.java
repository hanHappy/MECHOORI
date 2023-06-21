package com.mechoori.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.Rate;
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

        Menu menu = menuRepository.findById(rate.getMenuId());
        int cumulativeRatedPrice = menu.getCumulativeRatedPrice();
        
        int result = (ratePrice + cumulativeRatedPrice)/2;

        menu.setCumulativeRatedPrice(result);
        menuRepository.update(menu);
    }   
    
}
