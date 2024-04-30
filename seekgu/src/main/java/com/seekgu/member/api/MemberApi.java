package com.seekgu.member.api;

import com.seekgu.member.domain.Member;
import com.seekgu.member.domain.dto.MemberLoginDto;
import com.seekgu.member.domain.dto.MemberSignUpDto;
import com.seekgu.member.service.MemberService;
import com.seekgu.utils.ApiUtil;
import com.seekgu.utils.ApiUtil.ApiSuccessResult;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberApi {
    private final MemberService memberService;

    @PostMapping("/signIn")
    public ApiSuccessResult<Boolean> logIn(@RequestBody MemberLoginDto memberLoginDto, HttpSession session) {
        Member loginMember = memberService.login(memberLoginDto);
        session.setAttribute("memberId", loginMember.getMemberIdx());
        return ApiUtil.success(Boolean.TRUE);
    }

    @PostMapping("/signUp")
    public ApiSuccessResult<Boolean> signUp(@RequestBody MemberSignUpDto memberSignUpDto) {
        return ApiUtil.success(memberService.signUp(memberSignUpDto));
    }

    @GetMapping("/logout")
    public ApiSuccessResult<Boolean> logout(HttpSession session) {
        session.invalidate();
        return ApiUtil.success(Boolean.TRUE);
    }
}
