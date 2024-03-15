package ua.com.foxminded.universitycms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.repository.AdminRepository;

@Controller
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;

	@GetMapping("/admins")
	public String showAdmins(Model model) {
		List<Admin> admins = adminRepository.findAll();
		model.addAttribute("admins", admins);
		return "adminList";
	}
}