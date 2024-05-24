package ua.com.foxminded.universitycms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}

	@GetMapping("/menu")
	public String menu() {
		return "auth/menu";
	}

	@GetMapping("/logout")
	public String logout() {
		return "auth/logout";
	}

	@GetMapping("/error")
	public String error() {
		return "auth/error";
	}
}
