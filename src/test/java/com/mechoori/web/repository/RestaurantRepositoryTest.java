package com.mechoori.web.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantView;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestaurantRepositoryTest {

	@Autowired
	private RestaurantRepository repository;
	
	@Test
	void testFindAllIntegerInteger() {
		List<RestaurantView> list = repository.findAllRestaurantView(3);
		System.out.println(list);
	}

}
