package ua.com.foxminded.universitycms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/update/{id}")
	public String updateCourse(@PathVariable Long id, @RequestBody Course course) {
		Course updatedCourse = courseService.update(course);
		if (updatedCourse != null) {
			return "redirect:/courses/update/" + id;
		} else {
			return "redirect:/courses";
		}
	}

	@PostMapping("/delete")
	public String deleteCourse(@RequestParam Long id) {
		courseService.deleteById(id);
		return "redirect:/courses";
	}

	@GetMapping("/delete/{id}")
	public String showDeleteCourseConfirmation(@PathVariable Long id, Model model) {
		Optional<Course> course = courseService.findById(id);
		if (course.isPresent()) {
			model.addAttribute("course", course.get());
			return "deleteCourse";
		} else {
			return "redirect:/courses";
		}
	}

	@GetMapping("/assignTeacher/{courseId}")
	public String showAssignTeacherForm(@PathVariable Long courseId, Model model) {
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

	@PostMapping("/assignTeacher/{courseId}")
	public String assignTeacherToCourse(@PathVariable Long courseId, @RequestParam Long teacherId) {
		courseService.assignTeacher(courseId, teacherId);
		return "redirect:/courses/{courseId}";
	}

	@GetMapping("/assignGroups/{courseId}")
	public String showAssignGroupsForm(@PathVariable Long courseId, Model model) {
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

	@PostMapping("/assignGroups/{courseId}")
	public String assignGroupsToCourse(@PathVariable Long courseId, @RequestParam List<Long> groupIds) {
		courseService.assignGroups(courseId, groupIds);
		return "redirect:/courses/{courseId}";
	}

	@GetMapping("/getById/{id}")
	public String getCourseById(@PathVariable Long id, Model model) {
		Optional<Course> course = courseService.findById(id);
		if (course.isPresent()) {
			model.addAttribute("course", course.get());
			return "getCourseById";
		} else {
			return "error";
		}
	}
}