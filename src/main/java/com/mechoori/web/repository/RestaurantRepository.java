package com.mechoori.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mechoori.web.entity.Restaurant;

@Mapper
public interface RestaurantRepository {
	
	@Select("select * from restaurant where food_type_id = #{categoryId}")
	List<Restaurant> findAll(int categoryId);

	Restaurant getDetail();
	
}
