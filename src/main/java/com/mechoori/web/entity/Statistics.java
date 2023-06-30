package com.mechoori.web.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    private int memberId;
    private int overallRatedAvgPrice;
    private int overallAvgPrice;
    private int value;
}
