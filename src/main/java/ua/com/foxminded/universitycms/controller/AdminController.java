package ua.com.foxminded.universitycms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.model.User;
import ua.com.foxminded.universitycms.model.UserRole;
import ua.com.foxminded.universitycms.repository.AdminRepository;
import ua.com.foxminded.universitycms.repository.UserRepository;
import ua.com.foxminded.universitycms.service.AdminService;
import ua.com.foxminded.universitycms.service.UserService;

@Controller
@RequestMapping("/admins")
public class AdminController {

	private final UserService userService;

	private final UserRepository userRepository;

	private final AdminService adminService;

	private final AdminRepository adminRepository;

	public AdminController(AdminRepository adminRepository, UserRepository userRepository, UserService userService,
			AdminService adminService) {
		this.adminRepository = adminRepository;
		this.userRepository = userRepository;
		this.userService = userService;
		this.adminService = adminService;
	}

	@GetMapping
	public String showAdmins(Model model) {
		List<Admin> admins = adminRepository.findAll();
		model.addAttribute("admins", admins);
		return "adminList";
	}

	@GetMapping("admins/users")
	public String showUserManagementPage(Model model) {
		List<Admin> users = adminRepository.findAll();
		model.addAttribute("users", users);
		return "userProfile";
	}

	@GetMapping("/assignRole")
	public String showAssignRolePage(Model model) {
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "assignRole";
	}

	@PostMapping("/assignRole")
	public String assignRole(@RequestParam long id, @RequestParam UserRole role) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			user.get().setRole(role);
			userRepository.save(user.get());
		} else {
			System.out.println("User id is not available");
		}
		return "redirect:/admin/admins";
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@PostMapping("/{id}/update")
	public String updateAdmin(@ModelAttribute("admin") Admin admin) {
		adminService.update(admin);
		return "redirect:/admins";
	}

	@GetMapping("/{id}/update")
	public String showUpdateAdminForm(@PathVariable("id") Long id, Model model) {
		Optional<Admin> adminOptional = adminService.findById(id);
		if (adminOptional.isPresent()) {
			Admin admin = adminOptional.get();
			model.addAttribute("admin", admin);
			return "updateAdmin";
		} else {
			return "redirect:/admins";
		}
	}

	@PostMapping("/delete")
	public String deleteAdmin(@RequestParam Long id) {
		adminService.deleteById(id);
		return "redirect:/admins";
	}

	@GetMapping("/delete/{id}")
	public String showDeleteAdminConfirmation(@PathVariable Long id, Model model) {
		Optional<Admin> admin = adminService.findById(id);
		if (admin.isPresent()) {
			model.addAttribute("admin", admin.get());
			return "deleteAdmin";
		} else {
			return "redirect:/admins";
		}
	}

	@GetMapping("/getById/{id}")
	public String getAdminById(@PathVariable Long id, Model model) {
		Optional<Admin> admin = adminService.findById(id);
		if (admin.isPresent()) {
			model.addAttribute("admin", admin.get());
			return "getAdminById";
		} else {
			return "error";
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// User Management Endpoints
	@PostMapping("/users/create")
	public String createUser(@ModelAttribute("user") User user) {
		userService.create(user);
		return "redirect:/users";
	}

	@GetMapping("/users/create")
	public String showCreateUserForm(Model model) {
		model.addAttribute("user", new User());
		return "createUser";
	}

	@PostMapping("/users/{id}/update")
	public String updateUser(@ModelAttribute("user") User user) {
		userService.update(user);
		return "redirect:/users";
	}

	@GetMapping("/users/{id}/update")
	public String showUpdateUserForm(@PathVariable("id") Long id, Model model) {
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			model.addAttribute("user", user);
			return "updateUser";
		} else {
			return "redirect:/users";
		}
	}

	@PostMapping("/users/delete")
	public String deleteUser(@RequestParam Long id) {
		userService.deleteById(id);
		return "redirect:/users";
	}

	@GetMapping("/users/delete/{id}")
	public String showDeleteUserConfirmation(@PathVariable Long id, Model model) {
		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			model.addAttribute("user", user.get());
			return "deleteUser";
		} else {
			return "redirect:/users";
		}
	}

	@GetMapping("/users")
	public String showUsers(Model model) {
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "userList";
	}

	@GetMapping("/users/getById/{id}")
	public String getUserById(@PathVariable Long id, Model model) {
		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			model.addAttribute("user", user.get());
			return "getUserById";
		} else {
			return "error";
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	// Group Management Endpoints
//	@PostMapping("/create")
//	public String createGroup(@ModelAttribute("group") Group group) {
//		groupService.create(group);
//		return "redirect:/groups";
//	}
//
//	@GetMapping("/create")
//	public String showCreateGroupForm(Model model) {
//		model.addAttribute("group", new Group());
//		return "createGroup";
//	}
//
//	@PostMapping("/update")
//	public String updateGroup(@RequestBody Group group) {
//		groupService.update(group);
//		return "redirect:/groups";
//	}
//
//	@GetMapping("/update/{id}")
//	public String showUpdateGroupForm(@PathVariable Long id, Model model) {
//		Optional<Group> groupOptional = groupService.findById(id);
//		if (groupOptional.isPresent()) {
//			Group group = groupOptional.get();
//			model.addAttribute("group", group);
//			return "updateGroup";
//		} else {
//			return "redirect:/groups";
//		}
//	}
//
//	@PostMapping("/delete")
//	public String deleteGroup(@RequestParam Long id) {
//		groupService.deleteById(id);
//		return "redirect:/groups";
//	}
//
//	@GetMapping("/delete/{id}")
//	public String showDeleteGroupConfirmation(@PathVariable Long id, Model model) {
//		Optional<Group> group = groupService.findById(id);
//		if (group.isPresent()) {
//			model.addAttribute("group", group.get());
//			return "deleteGroup";
//		} else {
//			return "redirect:/groups";
//		}
//	}
//
//	@GetMapping("/getById/{id}")
//	public String getGroupById(@PathVariable Long id, Model model) {
//		Optional<Group> group = groupService.findById(id);
//		if (group.isPresent()) {
//			model.addAttribute("group", group.get());
//			return "getGroupById";
//		} else {
//			return "error";
//		}
//	}
//
//	// Course Management Endpoints (Admin only)
//	@PostMapping("/create")
//	public String createCourse(@ModelAttribute("course") Course course) {
//		courseService.create(course);
//		return "redirect:/courses";
//	}
//
//	@GetMapping("/create")
//	public String showCreateCourseForm(Model model) {
//		model.addAttribute("course", new Course());
//		return "createCourse";
//	}
//
//	@PostMapping("/update")
//	public String updateCourse(@RequestBody Course course) {
//		courseService.update(course);
//		return "redirect:/courses";
//	}
//
//	@GetMapping("/update/{id}")
//	public String showUpdateCourseForm(@PathVariable Long id, Model model) {
//		Optional<Course> courseOptional = courseService.findById(id);
//		if (courseOptional.isPresent()) {
//			Course course = courseOptional.get();
//			model.addAttribute("course", course);
//			return "updateCourse";
//		} else {
//			return "redirect:/courses";
//		}
//	}
//
//	@PostMapping("/delete")
//	public String deleteCourse(@RequestParam Long id) {
//		courseService.deleteById(id);
//		return "redirect:/courses";
//	}
//
//	@GetMapping("/delete/{id}")
//	public String showDeleteCourseConfirmation(@PathVariable Long id, Model model) {
//		Optional<Course> course = courseService.findById(id);
//		if (course.isPresent()) {
//			model.addAttribute("course", course.get());
//			return "deleteCourse";
//		} else {
//			return "redirect:/courses";
//		}
//	}
//
//	@GetMapping("/getById/{id}")
//	public String getCourseById(@PathVariable Long id, Model model) {
//		Optional<Course> course = courseService.findById(id);
//		if (course.isPresent()) {
//			model.addAttribute("course", course.get());
//			return "getCourseById";
//		} else {
//			return "error";
//		}
//	}
}
