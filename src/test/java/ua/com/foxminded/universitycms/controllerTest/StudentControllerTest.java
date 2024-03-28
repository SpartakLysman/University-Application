package ua.com.foxminded.universitycms.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.foxminded.universitycms.controller.StudentController;
import ua.com.foxminded.universitycms.model.Student;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private Student student;

    @Test
    @WithMockUser(roles = "STUDENT")
    void testSomeMethodToTest() {
    }

//	String expectedFirstname = "newFirstname";
//	String expectedLastname = "newLastname";
//
//	mockMvc.perform(post("/user/updateStudent")
//	                        .param("id", String.valueOf(userId))
//	                        .param("firstname", expectedFirstname)
//	                        .param("lastname", expectedLastname)
}
