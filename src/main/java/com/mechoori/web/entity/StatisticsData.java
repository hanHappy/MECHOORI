package com.mechoori.web.entity;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsData {
    private int id; 
    private Integer userAverage; // (총 합- 내꺼 합(있으면 더 하고 없으면 총합)) / 인원수
    private Integer myAverage; 
    private 
}
