package com.gd.article;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/auth/*") // 로그인 되었을때
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 요청전
		// 강제 형변환((HttpServletRequest)request)
		HttpServletRequest req = ((HttpServletRequest)request);
		HttpServletResponse res = ((HttpServletResponse)response);
		
		// 세션이 없으면 바로 redirect해버리기
		// 세션이 있으면 doFilter타기
		HttpSession session = req.getSession();
		if(session.getAttribute("loginUser") == null) {
			res.sendRedirect(req.getContextPath() + "/public/login");
			return;
		}
		
		chain.doFilter(request, response);
		
		// 요청후
		
		
		
		
	}

}
