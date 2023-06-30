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
    public void add(Rate rate, int memberId) {
        rate.setId(null);
        rate.setMemberId(memberId);
        repository.add(rate);
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
    public List<Rate> getList(int memberId) {
        return repository.getList(memberId);
    }
}
