package com.seekgu.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long memberIdx;
    private String memberNickName;
    private String memberId;
    private String memberPw;
    private Gender memberGender;
}
