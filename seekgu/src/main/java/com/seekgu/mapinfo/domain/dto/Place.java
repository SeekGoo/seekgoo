package com.seekgu.mapinfo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    private String placeName;
    private String roadAddressName;
    private int distance;
    private double x;
    private double y;
}
