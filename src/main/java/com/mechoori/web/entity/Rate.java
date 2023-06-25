package com.mechoori.web.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Rate {
    private Integer id;
    private int userId;
    private int menuId;
    private Date regDate;
    private int price;
    private String review;
    private Menu menu;
}
