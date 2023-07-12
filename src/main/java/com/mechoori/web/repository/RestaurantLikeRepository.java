package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mechoori.web.entity.LikeList;
import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.RestaurantLike;
import com.mechoori.web.security.MechooriUserDetails;

@Mapper
public interface RestaurantLikeRepository {

    int add(RestaurantLike restaurantlike);

    int count(int restaurantId);

    int delete(RestaurantLike restaurantLike);

    List<LikeList> findAll(int memberId);

}
