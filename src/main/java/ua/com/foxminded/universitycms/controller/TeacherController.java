package ua.com.foxminded.universitycms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.repository.TeacherRepository;

@Controller
public class TeacherController {

	@Autowired
	private TeacherRepository teacherRepository;

	@GetMapping("/teachers")
	public String showTeachers(Model model) {
		List<Teacher> teachers = teacherRepository.findAll();
		model.addAttribute("teachers", teachers);
		return "teacherList";
	}
}
