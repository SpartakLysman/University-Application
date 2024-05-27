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

import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.repository.GroupRepository;
import ua.com.foxminded.universitycms.service.GroupService;
import ua.com.foxminded.universitycms.service.ScheduleService;
import ua.com.foxminded.universitycms.service.StudentService;

@Controller
@RequestMapping("/groups")
public class GroupController {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private GroupService groupService;

	@Autowired
	private StudentService studentService;

	private final ScheduleService scheduleService;

	public GroupController(GroupService groupService, ScheduleService scheduleService) {
		this.groupService = groupService;
		this.scheduleService = scheduleService;
	}

	@GetMapping
	public String showGroups(Model model) {
		List<Group> groups = groupRepository.findAll();
		model.addAttribute("groups", groups);
		return "group/groupList";
	}

	@PostMapping("/create")
	public String createGroup(@ModelAttribute("group") Group group) {
		groupService.create(group);
		return "redirect:/groups";
	}

	@GetMapping("/create")
	public String showCreateGroupForm(Model model) {
		model.addAttribute("group", new Group());
		return "group/createGroup";
	}

	@PostMapping("/{id}/update")
	public String updateGroup(@ModelAttribute("group") Group group) {
		groupService.update(group);
		return "redirect:/groups";
	}

	@GetMapping("/{id}/update")
	public String showUpdateGroupForm(@PathVariable("id") Long id, Model model) {
		Optional<Group> groupOptional = groupService.findById(id);
		if (groupOptional.isPresent()) {
			Group group = groupOptional.get();
			model.addAttribute("group", group);
			return "group/updateGroup";
		} else {
			return "redirect:/groups";
		}
	}

	@PostMapping("/{id}/delete")
	public String deleteGroup(@RequestParam Long id) {
		groupService.deleteById(id);
		return "redirect:/groups";
	}

	@GetMapping("/{id}/delete")
	public String showDeleteGroupConfirmation(@PathVariable("id") Long id, Model model) {
		Optional<Group> group = groupService.findById(id);
		if (group.isPresent()) {
			model.addAttribute("group", group.get());
			return "group/deleteGroup";
		} else {
			return "redirect:/groups";
		}
	}

	@GetMapping("/getById/{id}")
	public String getGroupById(@PathVariable Long id, Model model) {
		Optional<Group> group = groupService.findById(id);
		if (group.isPresent()) {
			model.addAttribute("group", group.get());
			return "group/getGroupById";
		} else {
			return "auth/error";
		}
	}

	@PostMapping("/{id}/assignStudent")
	public String assignStudentToGroup(@PathVariable("id") Long groupId, @RequestParam Long studentId) {
		groupService.assignStudent(groupId, studentId);
		return "redirect:/groups";
	}

	@GetMapping("/{id}/assignStudent")
	public String showAssignStudentForm(@PathVariable("id") Long groupId, Model model) {
		Optional<Group> groupOptional = groupService.findById(groupId);
		if (groupOptional.isPresent()) {
			Group group = groupOptional.get();
			List<Student> students = studentService.findAll();
			model.addAttribute("group", group);
			model.addAttribute("students", students);
			return "group/assignStudent";
		} else {
			return "auth/error";
		}
	}

	@GetMapping("/{id}/schedule")
	public String viewSchedule(@PathVariable("id") Long id, Model model) {
		model.addAttribute("schedules", scheduleService.getGroupSchedule(id));
		return "schedule/groupSchedule";
	}
}
