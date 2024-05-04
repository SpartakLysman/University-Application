package ua.com.foxminded.universitycms.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
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

import ua.com.foxminded.universitycms.controller.AdminController;
import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.model.User;
import ua.com.foxminded.universitycms.model.UserRole;
import ua.com.foxminded.universitycms.repository.AdminRepository;
import ua.com.foxminded.universitycms.repository.UserRepository;
import ua.com.foxminded.universitycms.service.AdminService;
import ua.com.foxminded.universitycms.service.CourseService;
import ua.com.foxminded.universitycms.service.GroupService;
import ua.com.foxminded.universitycms.service.StudentService;
import ua.com.foxminded.universitycms.service.TeacherService;
import ua.com.foxminded.universitycms.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

	@InjectMocks
	private AdminController adminController;

	@Mock
	private AdminRepository adminRepository;

	@Mock
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private AdminService adminService;

	@Mock
	private GroupService groupService;

	@Mock
	private CourseService courseService;

	@Mock
	private TeacherService teacherService;

	@Mock
	private StudentService studentService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testShowAdmins() {
		List<Admin> admins = new ArrayList<>();
		admins.add(new Admin(1L, "Jamila", "Alila", "loggJamila", "passJamila"));
		admins.add(new Admin(2L, "Alex", "Gerokilot", "loggAlex", "passAlex"));

		when(adminRepository.findAll()).thenReturn(admins);

		Model model = mock(Model.class);

		String viewName = adminController.showAdmins(model);

		assertEquals("adminList", viewName);
		verify(model).addAttribute("admins", admins);
	}

	@Test
	public void testShowUserManagementPage() {
		List<Admin> admins = new ArrayList<>();
		admins.add(new Admin(1L, "Jamila", "Alila", "loggJamila", "passJamila"));
		admins.add(new Admin(2L, "Alex", "Gerokilot", "loggAlex", "passAlex"));

		when(adminRepository.findAll()).thenReturn(admins);

		Model model = mock(Model.class);

		String viewName = adminController.showUserManagementPage(model);

		assertEquals("userProfile", viewName);
		verify(model).addAttribute("users", admins);
	}

	@Test
	public void testShowAssignRolePage() {
		List<User> users = new ArrayList<>();
		users.add(new User(1L, "Jamila", "Alila", "loggJamila", "passJamila", UserRole.TEACHER));
		users.add(new User(2L, "Alex", "Gerokilot", "loggAlex", "passAlex", UserRole.TEACHER));

		when(userRepository.findAll()).thenReturn(users);

		Model model = mock(Model.class);

		String viewName = adminController.showAssignRolePage(model);

		assertEquals("assignRole", viewName);
		verify(model).addAttribute("users", users);
	}

	@Test
	public void testAssignRole() {

		User user = new User(1L, "Jamila", "Alila", "loggJamila", "passJamila", UserRole.TEACHER);

		when(userRepository.findById(1L)).thenReturn(Optional.of(user));

		String viewName = adminController.assignRole(1L, UserRole.ADMIN);

		assertEquals(UserRole.ADMIN, user.getRole());
		assertEquals("redirect:/admin/admins", viewName);
	}

	@Test
	public void testUpdateAdmin() {
		Admin admin = new Admin(1L, "Jamila", "Alila", "loggJamila", "passJamila");

		String viewName = adminController.updateAdmin(admin);

		assertEquals("redirect:/admins", viewName);
		verify(adminService).update(admin);
	}

	@Test
	public void testShowUpdateAdminForm() {
		Admin admin = new Admin(1L, "Jamila", "Alila", "loggJamila", "passJamila");
		when(adminService.findById(1L)).thenReturn(Optional.of(admin));

		Model model = mock(Model.class);

		String viewName = adminController.showUpdateAdminForm(1L, model);

		assertEquals("updateAdmin", viewName);
		verify(model).addAttribute("admin", admin);
	}

	@Test
	public void testDeleteAdmin() {
		Long id = 1L;

		String viewName = adminController.deleteAdmin(id);

		assertEquals("redirect:/admins", viewName);
		verify(adminService, times(1)).deleteById(id);
	}

	@Test
	public void testShowDeleteAdminConfirmation() {
		Admin admin = new Admin(1L, "Jamila", "Alila", "loggJamila", "passJamila");
		when(adminService.findById(1L)).thenReturn(Optional.of(admin));

		Model model = mock(Model.class);

		String viewName = adminController.showDeleteAdminConfirmation(1L, model);

		assertEquals("deleteAdmin", viewName);
		verify(model).addAttribute("admin", admin);
	}

	@Test
	public void testGetAdminById() {
		Admin admin = new Admin(1L, "Jamila", "Alila", "loggJamila", "passJamila");
		when(adminService.findById(1L)).thenReturn(Optional.of(admin));

		Model model = mock(Model.class);

		String viewName = adminController.getAdminById(1L, model);

		assertEquals("getAdminById", viewName);
		verify(model).addAttribute("admin", admin);
	}

	@Test
	public void testCreateUser() {
		User user = new User(1L, "Jamila", "Alila", "loggJamila", "passJamila", UserRole.TEACHER);

		String viewName = adminController.createUser(user);

		assertEquals("redirect:/users", viewName);
		verify(userService).create(user);
	}

	@Test
	public void testShowCreateUserForm() {
		Model model = mock(Model.class);

		User user = new User(1L, "Jamila", "Alila", "loggJamila", "passJamila", UserRole.TEACHER);
		user.setId(30L);
		String viewName = adminController.showCreateUserForm(model);

		assertEquals("createUser", viewName);
		verify(model, times(1)).addAttribute(eq("user"), any(User.class));
	}

	@Test
	public void testUpdateUser() {
		User user = new User(1L, "Jamila", "Alila", "loggJamila", "passJamila", UserRole.TEACHER);

		String viewName = adminController.updateUser(user);

		assertEquals("redirect:/users", viewName);
		verify(userService).update(user);
	}

	@Test
	public void testShowUpdateUserForm() {
		User user = new User(1L, "Jamila", "Alila", "loggJamila", "passJamila", UserRole.TEACHER);
		when(userService.findById(1L)).thenReturn(Optional.of(user));

		Model model = mock(Model.class);

		String viewName = adminController.showUpdateUserForm(1L, model);

		assertEquals("updateUser", viewName);
		verify(model).addAttribute("user", user);
	}

	@Test
	public void testDeleteUser() {
		String viewName = adminController.deleteUser(1L);

		assertEquals("redirect:/users", viewName);
		verify(userService).deleteById(1L);
	}

	@Test
	public void testShowDeleteUserConfirmation() {
		User user = new User(1L, "Jamila", "Alila", "loggJamila", "passJamila", UserRole.TEACHER);
		when(userService.findById(1L)).thenReturn(Optional.of(user));

		Model model = mock(Model.class);

		String viewName = adminController.showDeleteUserConfirmation(1L, model);

		assertEquals("deleteUser", viewName);
		verify(model).addAttribute("user", user);
	}

	@Test
	public void testShowUsers() {
		List<User> users = Arrays.asList(new User(1L, "Jamila", "Alila", "loggJamila", "passJamila", UserRole.TEACHER),
				new User(2L, "Alex", "Gerokilot", "loggAlex", "passAlex", UserRole.TEACHER));
		when(userRepository.findAll()).thenReturn(users);

		Model model = mock(Model.class);

		String viewName = adminController.showUsers(model);

		assertEquals("userList", viewName);
		verify(model).addAttribute("users", users);
	}

	@Test
	public void testGetUserById() {
		User user = new User(11L, "Jamila", "Alila", "loggJamila", "passJamila", UserRole.TEACHER);
		when(userService.findById(1L)).thenReturn(Optional.of(user));

		Model model = mock(Model.class);

		String viewName = adminController.getUserById(1L, model);

		assertEquals("getUserById", viewName);
		verify(model).addAttribute("user", user);
	}
}
