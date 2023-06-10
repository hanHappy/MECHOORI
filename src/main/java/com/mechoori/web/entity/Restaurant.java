package com.mechoori.web.entity;

import lombok.Data;

@Data
public class Restaurant {
    private Integer id;
	private Integer adminId;
	private Integer foodTypeId;
	private String name;
	private String intro;
	private String address;
	private String operatingTime;
	private String contactNumber;
	private Integer likedCount;
	private Integer ratedCount;
}

