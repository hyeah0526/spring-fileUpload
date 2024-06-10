package com.gd.article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.article.util.Debug;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class loginController {
	
	/* public는 로그인이 안되었을 때만 / auth는 로그인이 되었을 때만 */
	
	/* 로그인 폼 */
	@GetMapping("/public/login")
	public String login(HttpSession session) {
		return "/public/login";
	}

	/* 로그인 액션 */
	@PostMapping("/public/login")
	public String login(HttpSession session,
						@RequestParam(name="id") String id,
						@RequestParam(name="pw") String pw) {
		
		final String onId = "admin";
		final String onPw = "1234";
		if(id.equals(onId) && pw.equals(onPw)) {
			log.debug(Debug.PHA + "------로그인성공------ " + Debug.END);
			session.setAttribute("loginUser", id);
			log.debug(Debug.PHA + "id :" + id + Debug.END);
			
			// 회원 접속 카운터 ++1하기 위해서 이미 존재하는 application변수가 필요함
			
		}
		return "redirect:/auth/on";
	}
	
	
	/* 로그아웃 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/public/login";
	}
	
	
	/* 로그인 성공한 사람만 들어올 수 있는 페이지 */
	@GetMapping("/auth/on")
	public String on(HttpSession session) {
		return "/auth/on";
	}
}
