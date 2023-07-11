package com.mechoori.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.Statistics;
import com.mechoori.web.entity.Statistics2;
import com.mechoori.web.entity.Statistics3;
import com.mechoori.web.repository.MenuRepository;
import com.mechoori.web.repository.RateRepository;
import com.mechoori.web.repository.RestaurantRepository;

@Service
public class RateServiceImp implements RateService{
    
    @Autowired
    private RateRepository repository;
    
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

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


    @Override
    public List<Statistics3> getDate3(int memberId) {
        // Map<String, Integer> data = new HashMap<>();
        List<Statistics3> data = repository.findData3(memberId);

        System.out.println("data: "  + data);
        System.out.println("====================================================");

        String resName = data.get(0).getResName();
        data.get(0).setResName(resName);

        System.out.println(data.get(0).getResName());
        System.out.println(data.get(0).getValue());

        // int valueMe = list.get(0).getValue();
        // 가성비 계산
        // valueMe = (int)(((double) list.get(0).getOverallRatedAvgPrice() / list.get(0).getOverallAvgPrice()) * 100);
        // System.out.println(valueMe);

        // 가성비 set
        // list.get(0).setValue(valueMe);

        // System.out.println(list.get(0));

        // 가성비 get 후 data에 추가
        // data.put("me", list.get(0).getValue());
        // data.put("me", list.get(0).getOverallRatedAvgPrice());

        return data;
    }


    @Override
    public Map<String, Object> getRateResult(int restaurantId, int memberId) {

        Map<String, Object> result = new HashMap<>();
        // 각자의 데이터를 넣어줘야하기 떄문에 새롭게 map을 생성한다
        // 식당이름을 가져오기 위해 레스토랑Id를 받아온다

        // 식당명
        String name = restaurantRepository.findById(restaurantId).getName();
     
        // 메뉴명
        Rate rate = repository.findLatest(memberId); // 방금평가한 금액 (최신 레고드 가져오기)
        Menu menu = menuRepository.findById(rate.getMenuId());
        String menuName = menu.getName();
        
        // 메뉴가격
        int menuPrice = menu.getPrice();

        // 메뉴 평균 평가 가격
        // 1. menu_Id > rate테이블의 레코드를 조회
        // 2. 아래, 확장성 
        List<Rate> list = repository.findAll(menu.getId());
        
        // price 전부 더한거 / list.size
        int sum = 0;

        for(Rate r : list){
            sum += r.getPrice();
        }
        int avgRatePrice = sum / list.size(); //11033
        avgRatePrice = (int) Math.round(avgRatePrice / 100) * 100;

        // for(int i=0; i<list.size(); i++){
        //     sum += list.get(i).getPrice();
        // }

        // 나의 평가 가격
        int myRatePrice = rate.getPrice();

        // 나의 가성비
        double myValue_ = (double) rate.getPrice() / menuPrice * 100; // 100
        int myValue = (int)Math.round(myValue_); // 105

        result.put("name", name);
        result.put("menuName", menuName);
        result.put("menuPrice", menuPrice);
        result.put("avgRatePrice", avgRatePrice);
        result.put("myRatePrice", myRatePrice);
        result.put("myValue", myValue);


        return result;
    }

}
