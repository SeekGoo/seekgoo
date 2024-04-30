package com.seekgu.member.service;

import static org.junit.jupiter.api.Assertions.*;

import com.seekgu.member.domain.Gender;
import com.seekgu.member.domain.dto.MemberLoginDto;
import com.seekgu.member.domain.dto.MemberSignUpDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;


    @Test
    void signUp() {

        MemberSignUpDto memberSignUpDto = new MemberSignUpDto("정찬수","id01","pwd01","찰스", Gender.MALE);


        Assertions.assertThat(memberService.signUp(memberSignUpDto)).isTrue();

    }

    @Test
    void login() {
        MemberLoginDto memberLoginDto = new MemberLoginDto("id01","pwd01");

        Assertions.assertThat(memberService.login(memberLoginDto).getMemberId()).isEqualTo("id01");
    }
}