package com.seekgu.seekguboard.domain.dto;

import com.seekgu.seekguboard.domain.MealTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeekguBoardCreateDto {
    private String seekguTitle;
    private String seekguContent;
    private String seekguRestaurantName;
    private Double seekguRestaurantLatitude;
    private Double seekguRestaurantLongitude;
    private Long memberIdx;
    private Integer seekguMin;
    private Integer seekguMax;
    private Integer seekguLimitTime;
    private MealTime seekguMealTime;
}
