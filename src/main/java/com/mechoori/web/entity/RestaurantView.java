package com.mechoori.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantView {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String img;
    private String intro;
    private String address;
    private String operatingTime;
    private String contactNumber;
    private Integer avgPrice;
    private Integer avgRatedPrice;
    private Integer value;
    private Integer likeCount; // 식당 라이크 수
    private Integer like;       // 유저가 좋아요했으면 1 아니면 0
    private Integer rateCount;
    private Integer rate;
}