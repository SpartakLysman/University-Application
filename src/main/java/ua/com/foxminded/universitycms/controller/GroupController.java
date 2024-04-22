package ua.com.foxminded.universitycms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.repository.GroupRepository;
import ua.com.foxminded.universitycms.service.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupController {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private GroupService groupService;

	@GetMapping // ("/groups")
	public String showGroups(Model model) {
		List<Group> groups = groupRepository.findAll();
		model.addAttribute("groups", groups);
		return "groupList";
	}

	@PostMapping
	public ResponseEntity<Group> createGroup(@RequestBody Group group) {
		Group createdGroup = groupService.create(group);
		return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Group> updateGroup(@RequestBody Group group) {
		Group updatedGroup = groupService.update(group);
		if (updatedGroup != null) {
			return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGroup(@PathVariable Group group) {
		groupService.delete(group);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
		Optional<Group> group = groupService.findById(id);
		if (group != null) {
			return new ResponseEntity<>(group.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
