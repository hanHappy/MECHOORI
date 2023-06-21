package com.mechoori.web.entity;

import lombok.Data;

@Data
public class Menu {
    private Integer id;
    private Integer restaurantId;
    private Integer adminId;
    private String name;
    private Integer price;
    private Integer ratedPrice;
}
