package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.LikeListView;
import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.RestaurantLike;

public interface RestaurantLikeService {

    int add(RestaurantLike restaurantlike);

    int getCount(int restaurantId);

    int delete(RestaurantLike restaurantLike);

    List<LikeListView> getList(Member member);

}
