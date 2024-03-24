package ua.com.foxminded.universitycms.controllerTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ua.com.foxminded.universitycms.controller.GroupController;
import ua.com.foxminded.universitycms.model.Group;

@WebMvcTest(GroupController.class)
public class GroupControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private Group group;

}
