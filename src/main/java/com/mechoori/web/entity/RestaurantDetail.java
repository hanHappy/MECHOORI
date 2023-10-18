package com.mechoori.web.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDetail extends Restaurant{
    private int avgPrice;
    private int avgRatedPrice;
    private int value;
}
