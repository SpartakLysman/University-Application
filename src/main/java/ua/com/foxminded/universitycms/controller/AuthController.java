package ua.com.foxminded.universitycms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping("/auth/login")
	public String login() {
		return "auth/login";
	}

	@GetMapping("/auth/menu")
	public String menu() {
		return "auth/menu";
	}

	@GetMapping("/auth/logout")
	public String logout() {
		return "auth/logout";
	}

	@GetMapping("/auth/error")
	public String error() {
		return "auth/error";
	}
}
