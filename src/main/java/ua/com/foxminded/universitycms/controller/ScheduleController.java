package ua.com.foxminded.universitycms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.universitycms.model.ScheduleEntry;
import ua.com.foxminded.universitycms.service.CourseService;
import ua.com.foxminded.universitycms.service.ScheduleService;
import ua.com.foxminded.universitycms.service.StudentService;
import ua.com.foxminded.universitycms.service.TeacherService;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {
	private final ScheduleService scheduleService;
	private final CourseService courseService;
	private final StudentService studentService;
	private final TeacherService teacherService;

	public ScheduleController(ScheduleService scheduleService, CourseService courseService,
			StudentService studentService, TeacherService teacherService) {
		this.scheduleService = scheduleService;
		this.courseService = courseService;
		this.studentService = studentService;
		this.teacherService = teacherService;
	}

	@GetMapping("/create")
	public String showCreateForm(Model model) {
		model.addAttribute("scheduleEntry", new ScheduleEntry());
		model.addAttribute("courses", courseService.findAll());
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("teachers", teacherService.findAll());
		return "schedule/createSchedule";
	}

	@PostMapping("/create")
	public String createSchedule(@ModelAttribute("scheduleEntry") ScheduleEntry scheduleEntry, BindingResult result) {
		if (result.hasErrors()) {
			return "schedule/createSchedule";
		}
		scheduleService.save(scheduleEntry);
		return "redirect:/schedules";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		ScheduleEntry scheduleEntry = scheduleService.findById(id);
		model.addAttribute("scheduleEntry", scheduleEntry);
		model.addAttribute("courses", courseService.findAll());
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("teachers", teacherService.findAll());
		return "schedule/editSchedule";
	}

	@PostMapping("/edit/{id}")
	public String editSchedule(@PathVariable("id") Long id,
			@ModelAttribute("scheduleEntry") ScheduleEntry scheduleEntry, BindingResult result) {
		if (result.hasErrors()) {
			return "schedule/editSchedule";
		}
		scheduleEntry.setId(id);
		scheduleService.save(scheduleEntry);
		return "redirect:/schedules";
	}

	@GetMapping
	public String listSchedules(Model model) {
		List<ScheduleEntry> schedules = scheduleService.findAll();
		model.addAttribute("schedules", schedules);
		return "schedule/listSchedules";
	}
}
