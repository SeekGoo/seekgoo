package com.seekgu.member.domain.dto;

import com.seekgu.member.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberSignUpDto {
    private String memberName;
    private String memberId;
    private String memberPw;
    private String memberNickName;
    private Gender memberGender;
}
