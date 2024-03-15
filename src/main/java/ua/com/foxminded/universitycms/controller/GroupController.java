package ua.com.foxminded.universitycms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.repository.GroupRepository;

@Controller
public class GroupController {

	@Autowired
	private GroupRepository groupRepository;

	@GetMapping("/groups")
	public String showGroups(Model model) {
		List<Group> groups = groupRepository.findAll();
		model.addAttribute("groups", groups);
		return "groupList";
	}
}
