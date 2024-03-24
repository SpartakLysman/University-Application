package ua.com.foxminded.universitycms.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ua.com.foxminded.universitycms.controller.AdminController;
import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.service.GroupService;
import ua.com.foxminded.universitycms.service.StudentService;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService userService;

	@MockBean
	private GroupService groupService;

	@Test
	@WithMockUser(roles = "ADMIN")
	void testSomeMethodToTest() {

	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testDeleteStudent_AdminAccess() throws Exception {

		Student testStudent = new Student();
		testStudent.setName("Jamila");
		testStudent.setSurname("Ali");
		testStudent.setLogin("jamilLog");
		testStudent.setPassword("jamilPass");

		when(groupService.getGroupById(anyLong())).thenReturn(new Group());

		when(userService.save(any(Student.class))).thenReturn(testStudent);

		mockMvc.perform(post("/user/deleteStudent").param("userId", String.valueOf(testStudent.getId())))
				// Validate the response
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/user/student-management"))
				.andExpect(flash().attributeExists("successMessage"))
				.andExpect(flash().attribute("successMessage", "Account deleted successfully!"));

		verify(userService).existsByLogin(testStudent.getLogin());

		verify(userService).delete(testStudent);
	}
}
