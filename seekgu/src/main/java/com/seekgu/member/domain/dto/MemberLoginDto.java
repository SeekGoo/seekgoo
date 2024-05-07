package com.seekgu.member.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginDto {
    @NotEmpty(message = "아이디는 필수 입력값입니다.")
    private String memberId;
    @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
    private String memberPw;
}
