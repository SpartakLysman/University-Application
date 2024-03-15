package ua.com.foxminded.universitycms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.foxminded.universitycms.model.Course;
import ua.com.foxminded.universitycms.repository.CourseRepository;

@Controller
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping("/courses")
	public String showCourses(Model model) {
		List<Course> courses = courseRepository.findAll();
		model.addAttribute("courses", courses);
		return "courseList";
	}
}