package ua.com.foxminded.universitycms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.model.User;
import ua.com.foxminded.universitycms.model.UserRole;
import ua.com.foxminded.universitycms.repository.AdminRepository;
import ua.com.foxminded.universitycms.repository.UserRepository;

@Controller
public class AdminController {

	@Autowired
	private final AdminRepository adminRepository;

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	public AdminController(AdminRepository adminRepository, UserRepository userRepository) {
		this.adminRepository = adminRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("/admin/admins")
	public String showAdmins(Model model) {
		List<Admin> admins = adminRepository.findAll();
		model.addAttribute("admins", admins);
		return "adminList";
	}

	@GetMapping("/admin/users")
	public String showUserManagementPage(Model model) {
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "usersProfile";
	}

//	@PostMapping("/admin/assignRole")
//  @RequestMapping(value = "/admin/assignRole", method = RequestMethod.POST)
	@RequestMapping(value = "/admin/assignRole", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String assignRole(@RequestParam long id, @RequestParam UserRole role) {
		Optional<User> user = userRepository.findById(id);
		if (user != null) {
			user.get().setRole(role);
			userRepository.save(user);
		} else {
			System.out.println("User id is not availible");
		}
		return "assingRole";
	}
}