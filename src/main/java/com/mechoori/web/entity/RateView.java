package com.mechoori.web.entity;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateView {
    private Integer memberId;
    private Integer restaurantId;
    private Integer ratedAveragePrice;
    private Integer averagePrice;
}
