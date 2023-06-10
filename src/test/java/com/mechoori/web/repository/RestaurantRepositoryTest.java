package com.mechoori.web.repository;

import com.mechoori.web.entity.Menu;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.mechoori.web.entity.Restaurant;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestaurantRepositoryTest {
	
	@Autowired
	private MenuRepository repository;

	@Test
	void testFindById() {
		Menu menu = repository.findById(132);
		System.out.println(menu);
	}

}
