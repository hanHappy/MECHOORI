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
        rate.setMemberId(userId);
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

    // @Override
    // public List<Rate> getRatedPrice(Rate rate, Integer memberId) {

    //     memberId, restaurantId, ratedAveragePrice, averagePrice

    //     for(Rate rate : )

    //     restaurantId.size().

    //     return "";
    // }

    @Override
    public Map<String, Integer> getDate(int memberId) {
        
        Map<String, Integer> data = new HashMap<>();

        List<Statistics> list = repository.findData(memberId);

        int valueMe = list.get(0).getValue();
        int valueOther = list.get(1).getValue();
        // 가성비 계산
        valueMe = (int)(((double) list.get(0).getOverallRatedAvgPrice() / list.get(0).getOverallAvgPrice()) * 100);
        valueOther = (int)(((double) list.get(1).getOverallRatedAvgPrice() / list.get(1).getOverallAvgPrice()) * 100);

        System.out.println(valueMe);
        System.out.println(valueOther);
        // 가성비 set
        list.get(0).setValue(valueMe);
        list.get(1).setValue(valueOther);

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        // 가성비 get 후 data에 추가
        data.put("me", list.get(0).getValue()); 
        data.put("other", list.get(1).getValue()); 

        return data;
    }
}
