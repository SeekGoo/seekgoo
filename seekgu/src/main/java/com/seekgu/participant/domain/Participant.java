package com.seekgu.participant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    private Long participantIdx;
    private Long memberIdx;
    private Long seekguIdx;
}
