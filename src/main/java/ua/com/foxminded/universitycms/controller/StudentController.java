package ua.com.foxminded.universitycms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.repository.StudentRepository;

@Controller
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/students")
	public String showStudents(Model model) {
		List<Student> students = studentRepository.findAll();
		model.addAttribute("students", students);
		return "studentList";
	}
}
