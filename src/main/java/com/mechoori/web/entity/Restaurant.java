package com.mechoori.web.entity;

import lombok.Data;

@Data
public class Restaurant {
    private Integer id;
	// FIXME top category id 삭제 (category 테이블에 top category id 있음)
	private Integer topCategoryId;
	private Integer categoryId;
	private String name;
	private String img;
	private String intro;
	private String address;
	private String operatingTime;
	private String contactNumber;
}

