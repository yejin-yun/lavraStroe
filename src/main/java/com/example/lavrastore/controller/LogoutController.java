package com.example.lavrastore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

public class LogoutController {
	@RequestMapping("/shop/logout.do")
	public String handleRequest(HttpSession session) throws Exception {
		session.removeAttribute("userSession");
		session.invalidate();
		return "index";
	}
}
