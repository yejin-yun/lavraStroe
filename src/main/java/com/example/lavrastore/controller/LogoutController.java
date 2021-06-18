package com.example.lavrastore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {
	@RequestMapping("/shop/logout.do")
	public String handleRequest(HttpSession session) throws Exception {
		session.removeAttribute("userSession");
		session.invalidate();
		return "redirect:/";
	}
}
