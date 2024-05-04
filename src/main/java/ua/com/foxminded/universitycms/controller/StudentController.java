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

import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.repository.StudentRepository;
import ua.com.foxminded.universitycms.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	private final StudentRepository studentRepository;

	private final StudentService studentService;

	public StudentController(StudentRepository studentRepository, StudentService studentService) {
		this.studentRepository = studentRepository;
		this.studentService = studentService;
	}

	@GetMapping
	public String showStudents(Model model) {
		List<Student> students = studentRepository.findAll();
		model.addAttribute("students", students);
		return "studentList";
	}

	@PostMapping("/create")
	public String createStudent(@ModelAttribute("student") Student student) {
		studentService.create(student);
		return "redirect:/students";
	}

	@GetMapping("/create")
	public String showCreateStudentForm(Model model) {
		model.addAttribute("student", new Student());
		return "createStudent";
	}

	@PostMapping("/{id}/update")
	public String updateStudent(@ModelAttribute("student") Student student) {
		studentService.update(student);
		return "redirect:/students";
	}

	@GetMapping("/{id}/update")
	public String showUpdateStudentForm(@PathVariable("id") Long id, Model model) {
		Optional<Student> studentOptional = studentService.findById(id);
		if (studentOptional.isPresent()) {
			Student student = studentOptional.get();
			model.addAttribute("student", student);
			return "updateStudent";
		} else {
			return "redirect:/students";
		}
	}

	@PostMapping("/delete")
	public String deleteStudent(@RequestParam Long id) {
		studentService.deleteById(id);
		return "redirect:/students";
	}

	@GetMapping("/delete/{id}")
	public String showDeleteStudentConfirmation(@PathVariable Long id, Model model) {
		Optional<Student> student = studentService.findById(id);
		if (student.isPresent()) {
			model.addAttribute("student", student.get());
			return "deleteStudent";
		} else {
			return "redirect:/students";
		}
	}

	@GetMapping("/getById/{id}")
	public String getStudentById(@PathVariable Long id, Model model) {
		Optional<Student> student = studentService.findById(id);
		if (student.isPresent()) {
			model.addAttribute("student", student.get());
			return "getStudentById";
		} else {
			return "error";
		}
	}
}
