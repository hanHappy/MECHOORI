package com.mechoori.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeListView {
    private String restaurantName;
    private String img;
    private int avgRatedPrice;
    private int avgPrice;
    private int valuePercentage;
}
