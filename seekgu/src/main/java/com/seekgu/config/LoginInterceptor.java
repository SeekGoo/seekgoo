package com.seekgu.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seekgu.utils.ApiUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler
	) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("memberId") == null) {
			if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				ApiUtil.ApiErrorResult errorResult = ApiUtil.error(5000, "로그인이 필요한 페이지 입니다.");
				ObjectMapper mapper = new ObjectMapper();
				String errorResultJson = mapper.writeValueAsString(errorResult);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(errorResultJson);
			} else {
				String contextPath = request.getContextPath();
				String loginPage = contextPath + "/member/login";

				response.sendRedirect(loginPage);
			}
			return false;
		}
		return true;
	}
}
