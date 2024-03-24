package ua.com.foxminded.universitycms.controllerTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ua.com.foxminded.universitycms.controller.TeacherController;
import ua.com.foxminded.universitycms.model.Teacher;

@WebMvcTest(TeacherController.class)
public class TeacherControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private Teacher teacher;

	@Test
	@WithMockUser(roles = "TEACHER")
	void testSomeMethodToTest() {
	}

}
