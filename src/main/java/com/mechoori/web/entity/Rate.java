package com.mechoori.web.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Rate {
    private Integer id;
    private int memberId;
    private int menuId;
    private Date regDate;
    private int price;
    private String review;


//    이미지, 메뉴 이름, 식당이름, 가격, 내가 평가한 금액

}
