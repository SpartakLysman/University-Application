package ua.com.foxminded.universitycms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.model.UserRole;
import ua.com.foxminded.universitycms.model.UserTable;
import ua.com.foxminded.universitycms.repository.AdminRepository;
import ua.com.foxminded.universitycms.repository.UserTableRepository;

@Controller
public class AdminController {

	@Autowired
	private final AdminRepository adminRepository;

	@Autowired
	private final UserTableRepository userTableRepository;

	@Autowired
	public AdminController(AdminRepository adminRepository, UserTableRepository userTableRepository) {
		this.adminRepository = adminRepository;
		this.userTableRepository = userTableRepository;
	}

	@GetMapping("/admin/admins")
	public String showAdmins(Model model) {
		List<Admin> admins = adminRepository.findAll();
		model.addAttribute("admins", admins);
		return "adminList";
	}

	@GetMapping("/admin/users")
	public String showUserManagementPage(Model model) {
		List<Admin> users = adminRepository.findAll();
		model.addAttribute("users", users);
		return "usersProfile";
	}

	@GetMapping("/admin/assignRole")
	public String showAssignRolePage(Model model) {
		List<UserTable> users = userTableRepository.findAll();
		model.addAttribute("users", users);
		return "assignRole";
	}

	@PostMapping("/admin/assignRole")
	public String assignRole(@RequestParam long id, @RequestParam UserRole role) {
		Optional<UserTable> user = userTableRepository.findById(id);
		if (user.isPresent()) {
			user.get().setRole(role);
			userTableRepository.save(user.get());
		} else {
			System.out.println("User id is not available");
		}
		return "redirect:/admin/admins";
	}
}