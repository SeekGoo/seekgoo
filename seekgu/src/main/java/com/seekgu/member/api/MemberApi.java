package com.seekgu.member.api;

import com.seekgu.member.domain.Member;
import com.seekgu.member.domain.dto.MemberLoginDto;
import com.seekgu.member.domain.dto.MemberSignUpDto;
import com.seekgu.member.service.MemberService;
import com.seekgu.utils.ApiUtil;
import com.seekgu.utils.ApiUtil.ApiSuccessResult;
import com.seekgu.utils.slack.SlackUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberApi {
    private final MemberService memberService;
    private final SlackUtil slackUtil;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @ResponseBody
    @PostMapping("/loginImpl")
    public ApiSuccessResult<Boolean> loginImpl(@RequestBody MemberLoginDto memberLoginDto, HttpSession session) {
        Member loginMember = memberService.login(memberLoginDto);
        session.setAttribute("memberId", loginMember.getMemberIdx());
        return ApiUtil.success(Boolean.TRUE);
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }

    @ResponseBody
    @PostMapping("/signupImpl")
    public ApiSuccessResult<Boolean> signUpImpl(@RequestBody MemberSignUpDto memberSignUpDto) {
        return ApiUtil.success(memberService.signUp(memberSignUpDto));
    }

    @GetMapping("/logout")
    public ApiSuccessResult<Boolean> logout(HttpSession session) {
        session.invalidate();
        return ApiUtil.success(Boolean.TRUE);
    }
}
