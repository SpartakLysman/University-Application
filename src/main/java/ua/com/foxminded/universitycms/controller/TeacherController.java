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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.foxminded.universitycms.model.Course;
import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.repository.CourseRepository;
import ua.com.foxminded.universitycms.repository.TeacherRepository;
import ua.com.foxminded.universitycms.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

	@Autowired
	private final TeacherRepository teacherRepository;

	@Autowired
	private final TeacherService teacherService;

	@Autowired
	private final CourseRepository courseRepository;

	public TeacherController(TeacherRepository teacherRepository, TeacherService teacherService,
			CourseRepository courseRepository) {
		this.teacherRepository = teacherRepository;
		this.teacherService = teacherService;
		this.courseRepository = courseRepository;
	}

	@GetMapping
	public String showTeachers(Model model) {
		List<Teacher> teachers = teacherRepository.findAll();
		model.addAttribute("teachers", teachers);
		return "teacherList";
	}

	@PostMapping("/create")
	public String createTeacher(@ModelAttribute("teacher") Teacher teacher) {
		teacherService.create(teacher);
		return "redirect:/teachers";
	}

	@GetMapping("/create")
	public String showCreateTeacherForm(Model model) {
		model.addAttribute("teacher", new Teacher());
		return "createTeacher";
	}

	@PostMapping("/{id}/update")
	public String updateTeacher(@ModelAttribute("teacher") Teacher teacher) {
		teacherService.update(teacher);
		return "redirect:/teachers";
	}

	@GetMapping("/{id}/update")
	public String showUpdateTeacherForm(@PathVariable("id") Long id, Model model) {
		Optional<Teacher> teacherOptional = teacherService.findById(id);
		if (teacherOptional.isPresent()) {
			Teacher teacher = teacherOptional.get();
			model.addAttribute("teacher", teacher);
			return "updateTeacher";
		} else {
			return "redirect:/teachers";
		}
	}

	@PostMapping("/{id}/delete")
	public String deleteTeacher(@RequestParam Long id) {
		teacherService.deleteById(id);
		return "redirect:/teachers";
	}

	@GetMapping("/{id}/delete")
	public String showDeleteTeacherConfirmation(@PathVariable("id") Long id, Model model) {
		Optional<Teacher> teacher = teacherService.findById(id);
		if (teacher.isPresent()) {
			model.addAttribute("teacher", teacher.get());
			return "deleteTeacher";
		} else {
			return "redirect:/teachers";
		}
	}

	@GetMapping("/getById/{id}")
	public String getTeacherById(@PathVariable("id") Long id, Model model) {
		Optional<Teacher> teacher = teacherService.findById(id);
		if (teacher.isPresent()) {
			model.addAttribute("teacher", teacher.get());
			return "getTeacherById";
		} else {
			return "error";
		}
	}

	@GetMapping("/viewTeacherCourses/{id}")
	public String showViewTeacherCoursesForm(@PathVariable("id") Long teacherId, Model model) {
		Optional<Teacher> teacherOptional = teacherService.findById(teacherId);
		if (teacherOptional.isPresent()) {
			List<Course> courses = courseRepository.findByTeacherId(teacherId);
			model.addAttribute("courses", courses);
			return "viewTeacherCourses";
		} else {
			return "redirect:/teachers";
		}
	}
}
