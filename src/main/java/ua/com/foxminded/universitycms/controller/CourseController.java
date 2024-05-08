package ua.com.foxminded.universitycms.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.foxminded.universitycms.model.Course;
import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.repository.CourseRepository;
import ua.com.foxminded.universitycms.service.CourseService;
import ua.com.foxminded.universitycms.service.GroupService;
import ua.com.foxminded.universitycms.service.TeacherService;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseService courseService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private GroupService groupService;

	public CourseController(CourseRepository courseRepository, CourseService courseService,
			TeacherService teacherService, GroupService groupService) {
		this.courseRepository = courseRepository;
		this.courseService = courseService;
		this.teacherService = teacherService;
		this.groupService = groupService;
	}

	@GetMapping
	public String showCourses(Model model) {
		List<Course> courses = courseRepository.findAll();
		model.addAttribute("courses", courses);
		return "courseList";
	}

	@PostMapping("/create")
	public String createCourse(@ModelAttribute("course") Course course) {
		courseService.create(course);
		return "redirect:/courses";
	}

	@GetMapping("/create")
	public String showCreateCourseForm(Model model) {
		model.addAttribute("course", new Course());
		return "createCourse";
	}

	@PostMapping("/{id}/update")
	public String updateCourse(@PathVariable("id") Long courseId, @ModelAttribute("course") Course updatedCourse,
			Principal principal) {
		if (principal != null && principal instanceof Authentication) {
			Authentication authentication = (Authentication) principal;
			if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("TEACHER"))
					|| authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
				courseService.update(updatedCourse);
				return "redirect:/courses";
			}
		}
		return "error";
	}

	@GetMapping("/{id}/update")
	public String showUpdateCourseForm(@PathVariable("id") Long courseId, Model model, Principal principal) {
		if (principal != null && principal instanceof Authentication) {
			Authentication authentication = (Authentication) principal;
			if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("TEACHER"))
					|| authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
				Optional<Course> courseOptional = courseService.findById(courseId);
				if (courseOptional.isPresent()) {
					Course course = courseOptional.get();
					model.addAttribute("course", course);
					return "updateCourse";
				}
			}
		}
		return "redirect:/courses";
	}

	@PostMapping("/{id}/delete")
	public String deleteCourse(@RequestParam Long id) {
		courseService.deleteById(id);
		return "redirect:/courses";
	}

	@GetMapping("/{id}/delete")
	public String showDeleteCourseConfirmation(@PathVariable("id") Long id, Model model) {
		Optional<Course> course = courseService.findById(id);
		if (course.isPresent()) {
			model.addAttribute("course", course.get());
			return "deleteCourse";
		} else {
			return "redirect:/courses";
		}
	}

	@GetMapping("/getById/{id}")
	public String getCourseById(@PathVariable("id") Long id, Model model) {
		Optional<Course> course = courseService.findById(id);
		if (course.isPresent()) {
			model.addAttribute("course", course.get());
			return "getCourseById";
		} else {
			return "error";
		}
	}

	@GetMapping("/{id}/assignTeacher")
	public String showAssignTeacherForm(@PathVariable("id") Long courseId, Model model) {
		Optional<Course> courseOptional = courseService.findById(courseId);

		if (courseOptional.isPresent()) {
			Course course = courseOptional.get();

			List<Teacher> teachers = teacherService.findAll();

			model.addAttribute("course", course);
			model.addAttribute("teachers", teachers);

			return "assignTeacher";
		} else {
			return "error";
		}
	}

	@PostMapping("/{id}/assignTeacher")
	public String assignTeacherToCourse(@PathVariable("id") Long courseId, @RequestParam Long teacherId) {
		courseService.assignTeacher(courseId, teacherId);
		return "redirect:/courses";
	}

	@GetMapping("/{id}/assignGroup")
	public String showAssignGroupForm(@PathVariable("id") Long courseId, Model model) {
		Optional<Course> courseOptional = courseService.findById(courseId);

		if (courseOptional.isPresent()) {
			Course course = courseOptional.get();

			List<Group> groups = groupService.findAll();

			model.addAttribute("course", course);
			model.addAttribute("groups", groups);

			return "assignGroup";
		} else {
			return "error";
		}
	}

	@PostMapping("/{id}/assignGroup")
	public String assignGroupToCourse(@PathVariable("id") Long courseId, @RequestParam List<Long> groupIds) {
		courseService.assignGroup(courseId, groupIds);
		return "redirect:/courses";
	}
}