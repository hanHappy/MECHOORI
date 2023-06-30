package com.mechoori.web.repository;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignUpRepository {
    String checkNickName(String nickname);

}
