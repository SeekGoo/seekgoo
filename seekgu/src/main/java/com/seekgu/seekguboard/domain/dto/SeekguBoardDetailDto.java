package com.seekgu.seekguboard.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.seekgu.review.domain.Review;
import com.seekgu.seekguboard.domain.MealTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeekguBoardDetailDto {
    private Long seekguIdx;
    private String seekguTitle;
    private String seekguContent;
    private String seekguRestaurantName;
    private Double seekguRestaurantLatitude;
    private Double seekguRestaurantLongitude;
    private Long memberIdx;
    private String seekguMemberNickName;
    private Integer seekguMemberCount;
    private Integer seekguMin;
    private Integer seekguMax;
    private Integer seekguLimitTime;
    private LocalDateTime seekguRegDate;
    private MealTime seekguMealTime;
    private List<Review> reviewList;
}
