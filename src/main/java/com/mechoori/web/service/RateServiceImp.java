package com.mechoori.web.service;

import java.util.List;

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
    public void rate(Rate rate, int userId) {
        rate.setId(null);
        rate.setUserId(userId);
        repository.add(rate);
        // 메뉴테이블의 누적 가성비를 가져와서 더해줌?
        int ratePrice = rate.getPrice();

        // 메뉴 테이블의 누적 평가 가격
        int menuId = rate.getMenuId();
        Menu menu = menuRepository.findById(menuId);
        List<Rate> rateList = repository.findAll(menuId);
        // 평가된 횟수
        int ratedCount = rateList.size();
        // 누적 평가 가격
        int ratedPrice = menu.getRatedPrice();
        
        int result = ((ratedPrice * ratedCount) + ratePrice) / (ratedCount + 1);

        menu.setRatedPrice(result);
        menuRepository.update(menu);
    }   
    
}
