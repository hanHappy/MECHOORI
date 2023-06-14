package com.mechoori.web.service;

import com.mechoori.web.entity.Member;

public interface MemberService {
	   Member getById(int id);

	   Member getByUsername(String username);

	   String getRoleByUserName(String username);
}
