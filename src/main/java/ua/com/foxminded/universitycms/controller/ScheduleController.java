package ua.com.foxminded.universitycms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.universitycms.model.ScheduleEntry;
import ua.com.foxminded.universitycms.service.CourseService;
import ua.com.foxminded.universitycms.service.GroupService;
import ua.com.foxminded.universitycms.service.ScheduleService;
import ua.com.foxminded.universitycms.service.StudentService;
import ua.com.foxminded.universitycms.service.TeacherService;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {
	private final ScheduleService scheduleService;
	private final CourseService courseService;
	private final GroupService groupService;
	private final TeacherService teacherService;

	public ScheduleController(ScheduleService scheduleService, CourseService courseService, GroupService groupService,
			StudentService studentService, TeacherService teacherService) {
		this.scheduleService = scheduleService;
		this.courseService = courseService;
		this.groupService = groupService;
		this.teacherService = teacherService;
	}

	@GetMapping("/create")
	public String showCreateForm(Model model) {
		model.addAttribute("scheduleEntry", new ScheduleEntry());
		model.addAttribute("courses", courseService.findAll());
		model.addAttribute("groups", groupService.findAll());
		model.addAttribute("teachers", teacherService.findAll());
		return "schedule/createSchedule";
	}

	@PostMapping("/create")
	public String createSchedule(@ModelAttribute ScheduleEntry scheduleEntry) {
		scheduleService.save(scheduleEntry);
		return "redirect:/schedules";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		ScheduleEntry scheduleEntry = scheduleService.findById(id);
		model.addAttribute("scheduleEntry", scheduleEntry);
		model.addAttribute("courses", courseService.findAll());
		model.addAttribute("groups", groupService.findAll());
		model.addAttribute("teachers", teacherService.findAll());
		return "schedule/editSchedule";
	}

	@PostMapping("/edit/{id}")
	public String updateSchedule(@PathVariable("id") Long id, @ModelAttribute ScheduleEntry scheduleEntry) {
		scheduleEntry.setId(id);
		scheduleService.save(scheduleEntry);
		return "redirect:/schedules";
	}

	@GetMapping
	public String viewSchedules(Model model) {
		model.addAttribute("schedules", scheduleService.findAll());
		return "schedule/listSchedules";
	}

	@GetMapping("/group/{groupId}")
	public String viewGroupSchedule(@PathVariable("groupId") Long groupId, Model model) {
		model.addAttribute("schedules", scheduleService.getGroupSchedule(groupId));
		return "schedule/groupSchedule";
	}

	@GetMapping("/teacher/{teacherId}")
	public String viewTeacherSchedule(@PathVariable("teacherId") Long teacherId, Model model) {
		model.addAttribute("schedules", scheduleService.getTeacherSchedule(teacherId));
		return "schedule/teacherSchedule";
	}
}