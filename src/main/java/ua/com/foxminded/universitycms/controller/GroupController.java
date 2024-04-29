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

import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.repository.GroupRepository;
import ua.com.foxminded.universitycms.service.GroupService;
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

	@GetMapping
	public String showGroups(Model model) {
		List<Group> groups = groupRepository.findAll();
		model.addAttribute("groups", groups);
		return "groupList";
	}

	@PostMapping("/create")
	public String createGroup(@ModelAttribute("group") Group group) {
		groupService.create(group);
		return "redirect:/groups";
	}

	@GetMapping("/create")
	public String showCreateGroupForm(Model model) {
		model.addAttribute("group", new Group());
		return "createGroup";
	}

	@PostMapping("/update/{id}")
	public String updateGroup(@PathVariable Long id, @RequestBody Group group) {
		Group updatedGroup = groupService.update(group);
		if (updatedGroup != null) {
			return "redirect:/groups/update/" + id;
		} else {
			return "redirect:/groups";
		}
	}

	@PostMapping("/delete")
	public String deleteGroup(@RequestParam Long id) {
		groupService.deleteById(id);
		return "redirect:/groups";
	}

	@GetMapping("/delete/{id}")
	public String showDeleteGroupConfirmation(@PathVariable Long id, Model model) {
		Optional<Group> group = groupService.findById(id);
		if (group.isPresent()) {
			model.addAttribute("group", group.get());
			return "deleteGroup";
		} else {
			return "redirect:/groups";
		}
	}

	@GetMapping("/getById/{id}")
	public String getGroupById(@PathVariable Long id, Model model) {
		Optional<Group> group = groupService.findById(id);
		if (group.isPresent()) {
			model.addAttribute("group", group.get());
			return "getGroupById";
		} else {
			return "error";
		}
	}

	@PostMapping("/assignStudent/{groupId}")
	public String assignStudentToGroup(@PathVariable Long groupId, @RequestParam Long studentId) {
		groupService.assignStudent(groupId, studentId);
		return "redirect:/groups/{groupId}";
	}

	@GetMapping("/assignStudent/{groupId}")
	public String showAssignStudentForm(@PathVariable Long groupId, Model model) {
		Optional<Group> groupOptional = groupService.findById(groupId);
		if (groupOptional.isPresent()) {
			Group group = groupOptional.get();
			List<Student> students = studentService.findAll(); // Assuming you have a service for managing students
			model.addAttribute("group", group);
			model.addAttribute("students", students);
			return "assignStudent";
		} else {
			return "error";
		}
	}
}
