package ua.com.foxminded.universitycms.securityTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ua.com.foxminded.universitycms.util.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WithMockUser(username = "admin", roles = { "ADMIN" })
public class UsersTest {

	@Autowired
	private MockMvc mockMvc;

	private MessageService messageService;

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void getMessageUnauthenticated() {
		messageService.getMessage();
	}

	@Test

	public void getMessageWithMockUser() {
		String message = messageService.getMessage();

	}

	@Test
	@WithMockUser("customUsername")
	public void getMessageWithMockUserCustomUsername() {
		String message = messageService.getMessage();
	}

	@Test
	public void getMessageWithMockUserCustomUser() {
		String message = messageService.getMessage();

	}

	@Test
	@WithMockUser(username = "admin", authorities = { "ADMIN" })
	public void getMessageWithMockUserCustomAuthorities() {
		String message = messageService.getMessage();

	}

	@Test
	public void testLoginPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("login"));
	}
}
