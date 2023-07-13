package com.mechoori.web.entity;


import lombok.Data;

import java.util.Date;

@Data
public class RateListView {

    private int restaurantId;
    private int memberId;
    private int ratePrice;
    private int price;
    private String menuName;
    private String img;
    private String resName;
    private String review;
    private Date regDate;
    private int value;
}
