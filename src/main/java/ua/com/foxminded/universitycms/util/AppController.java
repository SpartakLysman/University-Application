package ua.com.foxminded.universitycms.util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}

	@RequestMapping("/login.html")
	public String login() {
		return "login.html";
	}

	@RequestMapping("/login-error.html")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login.html";
	}
}
