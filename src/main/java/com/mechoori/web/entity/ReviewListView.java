package com.mechoori.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListView {
    private int memberId;
    private String nickname;
    private String memberImg;
    private int restaurantId;
    private String restaurantName;
    private String menuName;
    private int pirce;
    private int ratePrice;
    private int value;
    private String review;
    private String reiviewImg;
    private Date regDate;
}
