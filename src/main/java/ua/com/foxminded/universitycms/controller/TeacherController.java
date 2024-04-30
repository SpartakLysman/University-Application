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

import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.repository.TeacherRepository;
import ua.com.foxminded.universitycms.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private TeacherService teacherService;

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

	@PostMapping("/update/{id}")
	public String updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
		Teacher updatedTeacher = teacherService.update(teacher);
		if (updatedTeacher != null) {
			return "redirect:/teachers/update/" + id;
		} else {
			return "redirect:/teachers";
		}
	}

	@PostMapping("/delete")
	public String deleteTeacher(@RequestParam Long id) {
		teacherService.deleteById(id);
		return "redirect:/teachers";
	}

	@GetMapping("/delete/{id}")
	public String showDeleteTeacherConfirmation(@PathVariable Long id, Model model) {
		Optional<Teacher> teacher = teacherService.findById(id);
		if (teacher.isPresent()) {
			model.addAttribute("teacher", teacher.get());
			return "deleteTeacher";
		} else {
			return "redirect:/teachers";
		}
	}

	@GetMapping("/getById/{id}")
	public String getTeacherById(@PathVariable Long id, Model model) {
		Optional<Teacher> teacher = teacherService.findById(id);
		if (teacher.isPresent()) {
			model.addAttribute("teacher", teacher.get());
			return "getTeacherById";
		} else {
			return "error";
		}
	}
}
