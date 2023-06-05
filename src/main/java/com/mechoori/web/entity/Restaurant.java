package com.mechoori.web.entity;

import lombok.Data;

@Data
public class Restaurant {
	private int id;
	private int adminId;
	private int foodTypeId;
	private String name;
	private String intro;
	private String address;
	private String operatingTime;
	private String contactNumber;
	private int likedCount;
	private int ratedCount;
}

