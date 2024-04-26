package ua.com.foxminded.universitycms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.model.Course;
import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.model.User;
import ua.com.foxminded.universitycms.model.UserRole;
import ua.com.foxminded.universitycms.repository.AdminRepository;
import ua.com.foxminded.universitycms.repository.UserRepository;
import ua.com.foxminded.universitycms.service.CourseService;
import ua.com.foxminded.universitycms.service.GroupService;
import ua.com.foxminded.universitycms.service.StudentService;
import ua.com.foxminded.universitycms.service.TeacherService;
import ua.com.foxminded.universitycms.service.UserService;

@Controller
@RequestMapping("/admins")
public class AdminController {

	private final UserService userService;

	private final GroupService groupService;

	private final CourseService courseService;

	private final TeacherService teacherService;

	private final StudentService studentService;

	private final UserRepository userRepository;

	private final AdminRepository adminRepository;

	public AdminController(AdminRepository adminRepository, UserRepository userRepository, UserService userService,
			GroupService groupService, CourseService courseService, TeacherService teacherService,
			StudentService studentService) {
		this.adminRepository = adminRepository;
		this.userRepository = userRepository;
		this.userService = userService;
		this.groupService = groupService;
		this.courseService = courseService;
		this.teacherService = teacherService;
		this.studentService = studentService;
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

	// User Management Endpoints

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser = userService.create(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.findById(id);
		if (user != null) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User updatedUser = userService.update(user);
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@RequestBody User user) {
		userService.delete(user);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Group Management Endpoints

	@PostMapping("/groups")
	public ResponseEntity<Group> createGroup(@RequestBody Group group) {
		Group createdGroup = groupService.create(group);
		return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
	}

	@GetMapping("/groups")
	public ResponseEntity<List<Group>> getAllGroups() {
		List<Group> groups = groupService.findAll();
		return new ResponseEntity<>(groups, HttpStatus.OK);
	}

	// Course Management Endpoints (Admin only)

	@PostMapping("/courses")
	public ResponseEntity<Course> createCourse(@RequestBody Course course) {
		Course createdCourse = courseService.create(course);
		return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
	}

	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAllCourses() {
		List<Course> courses = courseService.findAll();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
		Optional<Course> course = courseService.findById(id);
		if (course != null) {
			return new ResponseEntity<>(course.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/courses/{id}")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course) {// @PathVariable Long id,
		Course updatedCourse = courseService.update(course);
		if (updatedCourse != null) {
			return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@RequestBody Course course) {
		courseService.delete(course);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Teacher Management Endpoints

	@PostMapping("/teachers")
	public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
		Teacher createdTeacher = teacherService.create(teacher);
		return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
	}

	@GetMapping("/teachers")
	public ResponseEntity<List<Teacher>> getAllTeachers() {
		List<Teacher> teachers = teacherService.findAll();
		return new ResponseEntity<>(teachers, HttpStatus.OK);
	}

	// Student Management Endpoints

	@PostMapping("/students")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student createdStudent = studentService.create(student);
		return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
	}

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = studentService.findAll();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
}