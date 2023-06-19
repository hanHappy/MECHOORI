package com.mechoori.web.repository;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.mechoori.web.entity.Category;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest2 {
	
	@Autowired
	private CategoryRepository repository;

	@Test
	void testFindAll() {
		List<Category> list = repository.findAll();
		
		for(Category category : list)
			System.out.println(category.getName());
	}

}
