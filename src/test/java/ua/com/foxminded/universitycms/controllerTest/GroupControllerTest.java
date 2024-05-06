package ua.com.foxminded.universitycms.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import ua.com.foxminded.universitycms.controller.GroupController;
import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.repository.GroupRepository;
import ua.com.foxminded.universitycms.service.GroupService;
import ua.com.foxminded.universitycms.service.StudentService;

@RunWith(MockitoJUnitRunner.class)
public class GroupControllerTest {

	@InjectMocks
	private GroupController groupController;

	@Mock
	private GroupRepository groupRepository;

	@Mock
	private GroupService groupService;

	@Mock
	private StudentService studentService;

	@Mock
	private Model model;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testShowGroups() {
		List<Group> groups = new ArrayList<>();
		groups.add(new Group(1L, "Group 1"));
		groups.add(new Group(2L, "Group 2"));

		when(groupRepository.findAll()).thenReturn(groups);

		Model model = mock(Model.class);
		String viewName = groupController.showGroups(model);

		assertEquals("groupList", viewName);

		verify(model, times(1)).addAttribute("groups", groups);
	}

	@Test
	public void testCreateGroup() {
		Group group = new Group(1L, "Test Group");
		String viewName = groupController.createGroup(group);

		assertEquals("redirect:/groups", viewName);

		verify(groupService, times(1)).create(group);
	}

	@Test
	public void testShowCreateGroupForm() {
		Model model = mock(Model.class);
		String viewName = groupController.showCreateGroupForm(model);

		assertEquals("createGroup", viewName);

		verify(model, times(1)).addAttribute(eq("group"), any(Group.class));
	}

	@Test
	void testUpdateGroup() {
		Long groupId = 1L;
		Group group = new Group();
		group.setId(groupId);
		group.setTitle("Group 1");

		String result = groupController.updateGroup(group);

		assertEquals("redirect:/groups", result);
		verify(groupService, times(1)).update(group);
	}

	@Test
	void testShowUpdateGroupForm() {
		Long groupId = 1L;
		Group group = new Group();
		group.setId(groupId);
		Optional<Group> optionalGroup = Optional.of(group);
		Model model = mock(Model.class);
		when(groupService.findById(groupId)).thenReturn(optionalGroup);

		String result = groupController.showUpdateGroupForm(groupId, model);

		assertEquals("updateGroup", result);
		verify(model, times(1)).addAttribute("group", group);
	}

	@Test
	public void testDeleteGroup() {
		Long groupId = 1L;
		String viewName = groupController.deleteGroup(groupId);

		verify(groupService, times(1)).deleteById(groupId);

		assertEquals("redirect:/groups", viewName);
	}

	@Test
	public void testShowDeleteGroupConfirmation() {
		Long groupId = 1L;
		Group group = new Group(groupId, "Test Group");

		when(groupService.findById(groupId)).thenReturn(Optional.of(group));

		Model model = mock(Model.class);
		String viewName = groupController.showDeleteGroupConfirmation(groupId, model);

		verify(model, times(1)).addAttribute("group", group);

		assertEquals("deleteGroup", viewName);
	}

	@Test
	public void testGetGroupById_WhenGroupExists() {
		Long groupId = 1L;
		Group group = new Group(groupId, "Test Group");

		when(groupService.findById(groupId)).thenReturn(Optional.of(group));

		Model model = mock(Model.class);
		String viewName = groupController.getGroupById(groupId, model);

		verify(model, times(1)).addAttribute("group", group);

		assertEquals("getGroupById", viewName);
	}

	@Test
	public void testGetGroupById_WhenGroupDoesNotExist() {
		Long groupId = 1L;

		when(groupService.findById(groupId)).thenReturn(Optional.empty());

		Model model = mock(Model.class);
		String viewName = groupController.getGroupById(groupId, model);

		assertEquals("error", viewName);
	}

	@Test
	public void testShowAssignStudentForm() {
		Long groupId = 1L;
		Group group = new Group(groupId, "Test Group");

		List<Student> students = new ArrayList<>();

		Group groupNew1 = new Group(1L, "Test Group");
		Group groupNew2 = new Group(1L, "Test Group");
		students.add(new Student(1L, groupNew1, "Alex", "Ivanov", "loggAlex", "passAlex"));
		students.add(new Student(2L, groupNew2, "Jamila", "Aliila", "loggJamila", "passJamila"));

		when(groupService.findById(groupId)).thenReturn(Optional.of(group));

		when(studentService.findAll()).thenReturn(students);

		Model model = mock(Model.class);
		String viewName = groupController.showAssignStudentForm(groupId, model);

		verify(model, times(1)).addAttribute("group", group);
		verify(model, times(1)).addAttribute("students", students);

		assertEquals("assignStudent", viewName);
	}

	@Test
	public void testAssignStudentToGroup() {
		Long groupId = 1L;
		Long studentId = 1L;

		String viewName = groupController.assignStudentToGroup(groupId, studentId);

		verify(groupService, times(1)).assignStudent(groupId, studentId);

		assertEquals("redirect:/groups", viewName);
	}
}
