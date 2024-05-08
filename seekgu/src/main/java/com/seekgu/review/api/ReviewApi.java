package com.seekgu.review.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seekgu.review.domain.Review;
import com.seekgu.review.service.ReviewService;
import com.seekgu.utils.ApiUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewApi {

	private final ReviewService reviewService;

	@ResponseBody
	@PostMapping("/write")
	public ApiUtil.ApiSuccessResult<Boolean> createReview(@RequestBody Review review, HttpSession session) {
		Long memberIdx = (Long)session.getAttribute("memberId");
		return ApiUtil.success(reviewService.createReview(memberIdx, review));
	}
}
