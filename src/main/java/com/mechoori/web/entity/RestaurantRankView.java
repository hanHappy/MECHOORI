package com.mechoori.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRankView {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String img;
    private Integer avgPrice;
    private Integer avgRatedPrice;
    private Integer value;
}