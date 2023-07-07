package com.mechoori.web.service;

import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.RestaurantLike;

import java.util.List;

public interface MemberService {
	Member getById(int id);

	Member getByEmail(String email);

	String getRoleByEmail(String email);

	void add(Member member);

	void resetPwd(Member member);

	void restaurantLike(int Id);

    void update(Member member);
}
