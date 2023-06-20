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

        // rate 객체에 menuId;
        // (rate 객체의 price + menu 객체의 평가 가격)/2
        // 업데이트

        // 내 평가 가격
        int ratePrice = rate.getPrice();

        // 메뉴 테이블의 누적 평가 가격
        Menu menu = menuRepository.findById(rate.getMenuId());
        int cumulativeRatedPrice = menu.getCumulativeRatedPrice();
        
        int result = (ratePrice + cumulativeRatedPrice)/2;

        menu.setCumulativeRatedPrice(result);
        menuRepository.update(menu);
    }
    
}