package ua.com.foxminded.universitycms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import ua.com.foxminded.universitycms.dto.CourseBasicInfo;
import ua.com.foxminded.universitycms.service.CourseService;

@Controller
@RequestMapping("/public")
public class PublicPageController {

	private final CourseService courseService;

	public PublicPageController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping("/courses")
	public String listCourses(Model model) {
		List<CourseBasicInfo> courses = courseService.findAllBasicInfo();
		model.addAttribute("courses", courses);
		return "public/courses";
	}

	@RequestMapping("/error")
	public void handleErrorWithRedirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("/courses");
	}

	@RequestMapping(value = "/")
	public void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("/courses");
	}

	@GetMapping("/")
	public String homePage(Model model) {
		return "redirect:/public/courses";
	}
}