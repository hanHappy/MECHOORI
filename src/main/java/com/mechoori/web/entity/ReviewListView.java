package com.mechoori.web.entity;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// TODO RateListView와 합치기
public class ReviewListView {
    private int id;
    private int memberId;
    private String nickname;
    private String memberImg;
    private int restaurantId;
    private String restaurantName;
    private String menuName;
    private int price;
    private int id;
    private int ratePrice;
    private int value;
    private String review;
    private String reviewImg;
    private LocalDate regDate;
}