package com.mechoori.web.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.mechoori.web.entity.Category;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository repository;
	
	// @Test
	// void testSave() {
	// 	Category category = Category.builder()
	// 							.name("간식")
	// 							.image("간식.svg")
	// 							.build();
	// 	int save = repository.save(category);
	// 	System.out.println(category);
	// 	System.out.println(save);
	// }

}
