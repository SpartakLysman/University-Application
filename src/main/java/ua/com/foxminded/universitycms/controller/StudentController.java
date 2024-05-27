package ua.com.foxminded.universitycms.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.repository.CourseRepository;
import ua.com.foxminded.universitycms.repository.GroupRepository;
import ua.com.foxminded.universitycms.repository.StudentRepository;
import ua.com.foxminded.universitycms.service.GroupService;
import ua.com.foxminded.universitycms.service.ScheduleService;
import ua.com.foxminded.universitycms.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	private final StudentRepository studentRepository;

	private final StudentService studentService;

	private final CourseRepository courseRepository;

	private final GroupService groupService;

	private final GroupRepository groupRepository;

	private final ScheduleService scheduleService;

	private final static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	public StudentController(StudentRepository studentRepository, StudentService studentService,
			CourseRepository courseRepository, GroupService groupService, GroupRepository groupRepository,
			ScheduleService scheduleService) {
		this.studentRepository = studentRepository;
		this.studentService = studentService;
		this.courseRepository = courseRepository;
		this.groupService = groupService;
		this.groupRepository = groupRepository;
		this.scheduleService = scheduleService;
	}

	@GetMapping
	public String showStudents(Model model) {
		List<Student> students = studentRepository.findAll();
		model.addAttribute("students", students);
		return "student/studentList";
	}

	@PostMapping("/create")
	public String createStudent(@ModelAttribute("student") Student student, @RequestParam("group_id") Long groupId) {
		Group group = groupService.findById(groupId).orElse(null);
		student.setGroup(group);
		studentService.create(student);
		return "redirect:/students";
	}

	@GetMapping("/create")
	public String showCreateStudentForm(Model model) {
		model.addAttribute("student", new Student());
		return "student/createStudent";
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
			return "student/updateStudent";
		} else {
			return "redirect:/students";
		}
	}

	@PostMapping("/{id}/delete")
	public String deleteStudent(@RequestParam Long id) {
		studentService.deleteById(id);
		return "redirect:/students";
	}

	@GetMapping("/{id}/delete")
	public String showDeleteStudentConfirmation(@PathVariable("id") Long id, Model model) {
		Optional<Student> student = studentService.findById(id);
		if (student.isPresent()) {
			model.addAttribute("student", student.get());
			return "student/deleteStudent";
		} else {
			return "redirect:/students";
		}
	}

	@GetMapping("/getById/{id}")
	public String getStudentById(@PathVariable Long id, Model model) {
		Optional<Student> student = studentService.findById(id);
		if (student.isPresent()) {
			model.addAttribute("student", student.get());
			return "student/getStudentById";
		} else {
			return "student/studentDoesNotExist";
		}
	}

	@GetMapping("/coursesAvailableView")
	public String viewAllCourses(Model model) {
		List<Course> courses = courseRepository.findAll();
		model.addAttribute("courses", courses);
		return "student/availableCourses";
	}

	@PostMapping("/{studentId}/assignCourse")
	public String assignCourseToStudent(@PathVariable("studentId") Long studentId, @RequestParam Long courseId) {
		try {
			Optional<Student> studentOptional = studentRepository.findById(studentId);
			if (studentOptional.isPresent()) {
				Student student = studentOptional.get();
				studentService.enrollStudentInCourse(student, courseId);
				return "redirect:/students";
			} else {
				LOGGER.error("Student not found with ID: " + studentId);
				return "auth/error";
			}
		} catch (Exception e) {
			LOGGER.error("Error assigning course to student: " + e.getMessage());
			return "auth/error";
		}
	}

	@GetMapping("/{studentId}/assignCourse")
	public String showAssignCourseForm(@PathVariable("studentId") Long studentId, Model model) {
		try {
			Optional<Student> studentOptional = studentRepository.findById(studentId);

			if (studentOptional.isPresent()) {
				List<Course> courses = courseRepository.findAll();
				model.addAttribute("studentId", studentId);
				model.addAttribute("courses", courses);
				return "student/assignCourse";
			} else {
				return "auth/error";
			}
		} catch (Exception e) {
			LOGGER.error("Error showing assign course form", e);
			return "auth/error";
		}
	}

	@GetMapping("/{studentId}/assignGroup")
	public String showAssignStudentForm(@PathVariable Long studentId, Model model) {
		List<Group> groups = groupService.findAll();
		model.addAttribute("studentId", studentId);
		model.addAttribute("groups", groups);
		return "student/assignStudentToGroup";
	}

	@PostMapping("/{studentId}/assignGroup")
	public String assignStudentToGroup(@PathVariable Long studentId, @RequestParam Long groupId) {
		try {
			Optional<Student> studentOptional = studentRepository.findById(studentId);
			Optional<Group> groupOptional = groupRepository.findById(groupId);

			if (studentOptional.isPresent() && groupOptional.isPresent()) {
				Student student = studentOptional.get();
				Group group = groupOptional.get();

				if (student.getGroup() != null && student.getGroup().getId().equals(groupId)) {
					LOGGER.warn("Student is already assigned to the selected group.");
					return "redirect:/students";
				}
				student.setGroup(group);
				studentRepository.save(student);
				return "redirect:/students";
			} else {
				LOGGER.error("Student or Group not found with ID: " + studentId + " or " + groupId);
				return "auth/error";
			}
		} catch (Exception e) {
			LOGGER.error("Error assigning student to group: " + e.getMessage());
			return "auth/error";
		}
	}

	@GetMapping("/studentsInGroup")
	public String showSelectGroupForm(Model model) {
		List<Group> groups = groupService.findAll();
		model.addAttribute("groups", groups);
		return "student/studentsInGroupForm";
	}

	@PostMapping("/studentsInGroup{groupId}")
	public String showStudentsInGroup(@RequestParam("groupId") Long groupId, Model model) {
		List<Student> studentsInGroup = studentService.findByGroupId(groupId);
		model.addAttribute("students", studentsInGroup);
		return "student/viewStudetsInGroup";
	}

	@GetMapping("/{id}/schedule")
	public String viewSchedule(@PathVariable("id") Long id, Model model) {
		Optional<Student> student = studentService.findById(id);
		if (student == null) {
			throw new IllegalArgumentException("Invalid student Id:" + id);
		}
		Long groupId = student.get().getGroup().getId();
		model.addAttribute("schedules", scheduleService.getGroupSchedule(groupId));
		return "schedule/studentSchedule";
	}
}
