package com.mechoori.web.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewListRepositoryTest {

    @Autowired
    private ReviewListRepository repository;
	
	@Test
	void testFindAll() {
		System.out.println(repository.findAll(1));
	}

}
