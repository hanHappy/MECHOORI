package com.mechoori.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "html/mypage-unlogined";
	}
	
}
