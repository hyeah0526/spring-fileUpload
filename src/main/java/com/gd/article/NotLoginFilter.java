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

@WebFilter("/public/*")
public class NotLoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 요청전
		// 강제 형변환((HttpServletRequest)request)
		HttpServletRequest req = ((HttpServletRequest)request);
		HttpServletResponse res = ((HttpServletResponse)response);
				
		// 세션이 있으면 auto로 보냄
		// 세션이 없으면 doFilter타기
		HttpSession session = req.getSession();
		if(session.getAttribute("loginUser") != null) {
			res.sendRedirect(req.getContextPath() + "/auth/on");
			return;
		}
		
		// 요청후
		chain.doFilter(request, response);
		
	}

}
