package com.mechoori.web.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistics3 {
    private int memberId;
    private int restaurantId;

    private int avgPrice;
    private int avgRatedPrice;

    private int value;
    private String resName;
}