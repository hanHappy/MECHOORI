package com.mechoori.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateView {

    private int memberId;
    private int reataurantId;
    private int ratedAveragePrice;
    private int averagePrice;

}
