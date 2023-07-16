package com.mechoori.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.RateListView;
import com.mechoori.web.entity.ReviewListView;
import com.mechoori.web.entity.Statistics;
import com.mechoori.web.entity.Statistics2;
import com.mechoori.web.entity.Statistics3;
import com.mechoori.web.repository.MenuRepository;
import com.mechoori.web.repository.RateRepository;
import com.mechoori.web.repository.RestaurantRepository;
import com.mechoori.web.repository.RestaurantRepository;

@Service
public class RateServiceImp implements RateService {

    @Autowired
    private RateRepository repository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void add(Rate rate, int memberId) {
        rate.setId(null);
        rate.setMemberId(memberId);
        repository.add(rate);
    }

    @Override
    public int delete(int id) {
        return repository.delete(id);
    }

    @Override
    public List<Rate> getListByMenuIds(List<Integer> menuIds) {
        return repository.findByMenuIds(menuIds);
    }

    // chart1
    @Override
    public Map<String, Integer> getDate(int memberId) {

        Map<String, Integer> data = new HashMap<>();
        List<Statistics> list = repository.findData(memberId);

        int valueMe = list.get(0).getValue();
        int valueOther = list.get(1).getValue();
        // 가성비 계산
        valueMe = (int) (((double) list.get(0).getOverallRatedAvgPrice() / list.get(0).getOverallAvgPrice()) * 100);
        valueOther = (int) (((double) list.get(1).getOverallRatedAvgPrice() / list.get(1).getOverallAvgPrice()) * 100);

        // 가성비 set
        list.get(0).setValue(valueMe);
        list.get(1).setValue(valueOther);

        // 가성비 get 후 data에 추가
        data.put("me", list.get(0).getValue());
        data.put("other", list.get(1).getValue());
        // data.put("me", list.get(0).getOverallRatedAvgPrice());
        // data.put("other", list.get(1).getOverallRatedAvgPrice());
        return data;
    }

    // chart2
    @Override
    public List<Statistics2> getDate2(int memberId) {
        List<Statistics2> list = repository.findData2(memberId);

        return list;
    }

    // chart3
    @Override
    public List<Statistics3> getDate3(int memberId) {

        List<Statistics3> data = repository.findData3(memberId);

        String resName = data.get(0).getResName();
        data.get(0).setResName(resName);

        return data;
    }

    @Override
    public List<RateListView> getMyList(int memberId, int offset) {
        List<RateListView> list = repository.getMyList(memberId, offset, 6);
        for(RateListView item : list){
            double value_ = (double)item.getRatePrice() / item.getPrice() * 100;
            int value = (int) Math.round(value_);
            item.setValue(value);
        }
        return list;
    }


    @Override
    public Map<String, Object> getRateResult(int restaurantId, int memberId) {

        Map<String, Object> result = new HashMap<>();

        // 식당명
        String name = restaurantRepository.findById(restaurantId).getName();

        // 메뉴명
        Rate rate = repository.findLatest(memberId);
        Menu menu = menuRepository.findById(rate.getMenuId());
        String menuName = menu.getName();

        // 메뉴 가격
        int menuPrice = menu.getPrice();

        // 메뉴 평균 평가 가격
        // 1. menu_id -> rate 테이블의 레코드를 조회
        List<Rate> list = repository.findAll(menu.getId());

        int sum = 0;
        for (Rate r : list) {
            sum += r.getPrice();
        }
        int avgRatedPrice = sum / list.size();
        avgRatedPrice = (int) Math.round(avgRatedPrice / 100) * 100;

        // 나의 평가 가격
        int myRatePrice = rate.getPrice();

        // 나의 가성비
        double myValue_ = (double) rate.getPrice() / menuPrice * 100;
        int myValue = (int) Math.round(myValue_);

        result.put("name", name);
        result.put("menuName", menuName);
        result.put("menuPrice", menuPrice);
        result.put("avgRatedPrice", avgRatedPrice);
        result.put("myRatePrice", myRatePrice);
        result.put("myValue", myValue);

        return result;
    }

    @Override
    public List<ReviewListView> getViewList(int restaurantId) {
        return repository.findViewAll(restaurantId);
    }


}
