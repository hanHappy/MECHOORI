package com.mechoori.web.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Review {

    private String nickname;
    private String memberImg;
    private String restaurantName;
    private String menuName;
    private int memberId;
    private int price;
    private int ratePrice;
    private int value;
    private String review;
    private String reviewImg;
    private int restaurantId;
    private Date regDate;



}
