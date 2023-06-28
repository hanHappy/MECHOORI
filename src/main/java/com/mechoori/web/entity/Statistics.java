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
    private int overAllRatedAvgPrice; // (총 합- 내꺼 합(있으면 더 하고 없으면 총합)) / 인원수
    private int overAllAvgPrice;
    private int value;
}
