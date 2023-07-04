package com.mechoori.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.Statistics;
import com.mechoori.web.entity.Statistics2;
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
    

    @Override
    public List<Rate> getListByMenuIds(List<Integer> menuIds) {
        return repository.findByMenuIds(menuIds);
    }


    @Override
    public Map<String, Integer> getDate(int memberId) {

        Map<String, Integer> data = new HashMap<>();
        List<Statistics> list = repository.findData(memberId);

        // System.out.println("list : " + list);

        int valueMe = list.get(0).getValue();
        int valueOther = list.get(1).getValue();
        // 가성비 계산
        valueMe = (int)(((double) list.get(0).getOverallRatedAvgPrice() / list.get(0).getOverallAvgPrice()) * 100);
        valueOther = (int)(((double) list.get(1).getOverallRatedAvgPrice() / list.get(1).getOverallAvgPrice()) * 100);

        // System.out.println(valueMe);
        // System.out.println(valueOther);

        // 가성비 set
        list.get(0).setValue(valueMe);
        list.get(1).setValue(valueOther);

        // System.out.println(list.get(0));
        // System.out.println(list.get(1));

        // 가성비 get 후 data에 추가
        data.put("me", list.get(0).getValue());
        data.put("other", list.get(1).getValue());
        // data.put("me", list.get(0).getOverallRatedAvgPrice());
        // data.put("other", list.get(1).getOverallRatedAvgPrice());

        return data;
    }

    @Override
    public List<Statistics2> getDate2(int memberId) {
        
        // List<Statistics2> list = new ArrayList<>(memberId);
       // Map<String, Integer> data = new HashMap<>();
        List<Statistics2> list = repository.findData2(memberId);

        System.out.println(list.get(0).getName()); // 한식
        System.out.println(list.get(0).getRateCount()); // 6
        System.out.println(list.get(0).getName()); // 6
        
        // for(Statistics2 data : list){
        //     System.out.println(data);
        // }


        return list;
    }

    @Override
    public List<Rate> getList(int memberId) {
        return repository.getList(memberId);
    }

}
