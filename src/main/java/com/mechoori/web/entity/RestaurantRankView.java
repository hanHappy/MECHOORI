package com.mechoori.web.entity;

import lombok.Data;

@Data
public class RestaurantRankView {


    private int topCategoryId;
    private int categoryId;
    private String name;
    private String img;
    private int avgPrice;
    private int avgRatedPrice;
    private int value;


}
