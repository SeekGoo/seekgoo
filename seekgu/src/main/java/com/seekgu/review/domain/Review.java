package com.seekgu.review.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long reviewIdx;
    private Long seekguIdx;
    private Integer reviewRate;
    private String memberNickname;
    private String reviewComment;
}
