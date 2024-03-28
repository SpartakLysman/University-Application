package ua.com.foxminded.universitycms.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@PostMapping("/process-login")
	public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

		System.out.println("Received username: " + username);
		System.out.println("Received password: " + password);

		return "redirect:/menu.html";
	}
}