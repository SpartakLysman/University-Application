package ua.com.foxminded.universitycms.controllerTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ua.com.foxminded.universitycms.controller.CourseController;
import ua.com.foxminded.universitycms.model.Course;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private Course course;

}
