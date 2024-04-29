package com.seekgu.seekguboard.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeekguBoard {
    private Long seekguIdx;
    private String seekguTitle;
    private String seekguContent;
    private String seekguRestaurantName;
    private Double seekguRestaurantLatitude;
    private Double seekguRestaurantLongitude;
    private Long memberIdx;
    private Integer seekguMemberCount;
    private Integer seekguMin;
    private Integer seekguMax;
    private Integer seekguLimitTime;
    private LocalDateTime seekguRegTime;
    private LocalDateTime seekguMealTime;
}
