package com.seekgu.member.domain.dto;

import com.seekgu.member.domain.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberSignUpDto {
    @NotEmpty(message = "이름은 필수 입력값입니다.")
    private String memberName;
    @NotEmpty(message = "아이디는 필수 입력값입니다.")
    private String memberId;
    @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
    private String memberPw;
    @NotEmpty(message = "닉네임은 필수 입력값입니다.")
    private String memberNickName;
    private Gender memberGender;
}
