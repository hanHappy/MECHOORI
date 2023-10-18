package com.mechoori.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.mechoori.web.entity.RestaurantLike;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestaurantLikeRepositoryTest {

	@Autowired
	private RestaurantLikeRepository repository;
	
	@Test
	void test() {
		
		RestaurantLike restaurantLike = RestaurantLike.builder()
													  .memberId(144)
													  .restaurantId(140)
													  .build();
		
		int result = repository.delete(restaurantLike);
		System.out.println(result);
	}

}
