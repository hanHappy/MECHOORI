package com.mechoori.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeList {

    private int memberId;
    private int restaurantId;
    private String restaurantName;
    private String img;
    private int avgRatedPrice;
    private int avgPrice;
    private int value;

}