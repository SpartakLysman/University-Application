package ua.com.foxminded.universitycms.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.service.AdminService;

@SpringBootTest(classes = { AdminService.class })
public class AdminServiceTest {

	@MockBean
	@Autowired
	AdminService userService;

	private List<Admin> usersList;
	private Admin userTest = new Admin();

	@Test
	void findAdminByIdTest() {
		userTest.setId(5L);
		when(userService.findById(5L)).thenReturn(Optional.of(userTest));

		Optional<Admin> newUser = userService.findById(5L);

		assertEquals(newUser.get().getId(), userTest.getId());
		assertEquals(newUser.get().getName(), userTest.getName());

		verify(userService).findById(5L);
	}
}
