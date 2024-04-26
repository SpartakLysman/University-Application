package ua.com.foxminded.universitycms.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ua.com.foxminded.universitycms.controller.AdminController;
import ua.com.foxminded.universitycms.model.Course;
import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.model.User;
import ua.com.foxminded.universitycms.service.CourseService;
import ua.com.foxminded.universitycms.service.GroupService;
import ua.com.foxminded.universitycms.service.StudentService;
import ua.com.foxminded.universitycms.service.TeacherService;
import ua.com.foxminded.universitycms.service.UserService;

@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest(classes = { AdminController.class })
public class AdminControllerTest {

	@InjectMocks
	private AdminController adminController;

	@Mock
	private UserService userService;

	@Mock
	private GroupService groupService;

	@Mock
	private CourseService courseService;

	@Mock
	private TeacherService teacherService;

	@Mock
	private StudentService studentService;

	@Before
	public void setUp() {
		userService = mock(UserService.class);
		groupService = mock(GroupService.class);
		courseService = mock(CourseService.class);
		teacherService = mock(TeacherService.class);
		studentService = mock(StudentService.class);

		adminController = new AdminController(null, null, userService, groupService, courseService, teacherService,
				studentService);
	}

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setId(1L);
		when(userService.create(any(User.class))).thenReturn(user);

		ResponseEntity<User> response = adminController.createUser(new User());

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(user, response.getBody());
	}

	// User Management Tests

	@Test
	public void testGetAllUsers() {
		List<User> users = Arrays.asList(new User(), new User());
		when(userService.findAll()).thenReturn(users);

		ResponseEntity<List<User>> response = adminController.getAllUsers();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(users, response.getBody());
	}

	// Group Management Tests

	@Test
	public void testCreateGroup() {
		Group group = new Group();
		group.setId(1L);
		when(groupService.create(any(Group.class))).thenReturn(group);

		ResponseEntity<Group> response = adminController.createGroup(new Group());

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(group, response.getBody());
	}

	// Course Management Tests

	@Test
	public void testGetAllCourses() {
		List<Course> courses = Arrays.asList(new Course(), new Course());
		when(courseService.findAll()).thenReturn(courses);

		ResponseEntity<List<Course>> response = adminController.getAllCourses();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(courses, response.getBody());
	}

	// Teacher Management Tests

	@Test
	public void testGetAllTeachers() {
		List<Teacher> teachers = Arrays.asList(new Teacher(), new Teacher());
		when(teacherService.findAll()).thenReturn(teachers);

		ResponseEntity<List<Teacher>> response = adminController.getAllTeachers();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(teachers, response.getBody());
	}

	// Student Management Tests

	@Test
	public void testGetAllStudents() {
		List<Student> students = Arrays.asList(new Student(), new Student());
		when(studentService.findAll()).thenReturn(students);

		ResponseEntity<List<Student>> response = adminController.getAllStudents();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(students, response.getBody());
	}
}
