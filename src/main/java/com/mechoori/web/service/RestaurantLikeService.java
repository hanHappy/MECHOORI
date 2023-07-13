package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.LikeList;
import com.mechoori.web.entity.RestaurantLike;
import com.mechoori.web.security.MechooriUserDetails;

public interface RestaurantLikeService {

    int add(RestaurantLike restaurantlike);

    int getCount(int restaurantId);

    int delete(RestaurantLike restaurantLike);

	List<LikeList> getList(int memberId);

}
