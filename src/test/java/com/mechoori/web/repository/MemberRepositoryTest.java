package com.mechoori.web.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.mechoori.web.entity.Member;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {
 
    @Autowired
    private MemberRepository repository;

    @Test
    public void testFindByEmail(){
        Member member = repository.findByEmail("sangmin950706@gmail.com");
        System.out.println(member);
        System.out.println(member.getUsername());
    }


}
