package com.mechoori.web.entity;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuView{
    private int id;
    private int restaurantId;
    private String name;
    private int price;
    private int ratedPrice;
    private int value;
}
