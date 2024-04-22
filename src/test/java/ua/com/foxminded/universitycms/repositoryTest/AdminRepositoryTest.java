package ua.com.foxminded.universitycms.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.repository.AdminRepository;

@SpringBootTest(classes = { AdminRepository.class })
public class AdminRepositoryTest {

	@Mock
	private AdminRepository adminRepository;

	@Test
	public void testFindByName() {
		String name = "Admin Smith";

		when(adminRepository.findByName(name)).thenReturn(Arrays.asList(new Admin()));

		List<Admin> admins = adminRepository.findByName(name);

		verify(adminRepository).findByName(name);
		assertNotNull(admins);
		assertFalse(admins.isEmpty());
	}

	@Test
	public void testFindByLogin() {
		String login = "admin_smith";

		when(adminRepository.findByLogin(login)).thenReturn(Optional.of(new Admin()));

		Optional<Admin> admin = adminRepository.findByLogin(login);

		verify(adminRepository).findByLogin(login);
		assertTrue(admin.isPresent());
	}

	@Test
	public void testFindByLoginAndPassword() {
		String login = "admin_smith";
		String password = "password123";

		when(adminRepository.findByLoginAndPassword(login, password)).thenReturn(Optional.of(new Admin()));

		Optional<Admin> admin = adminRepository.findByLoginAndPassword(login, password);

		verify(adminRepository).findByLoginAndPassword(login, password);
		assertTrue(admin.isPresent());
	}

	@Test
	public void testFindFirstByOrderByKeyDesc() {
		when(adminRepository.findFirstByOrderByIdDesc()).thenReturn(Optional.of(new Admin()));

		Optional<Admin> admin = adminRepository.findFirstByOrderByIdDesc();

		verify(adminRepository).findFirstByOrderByIdDesc();
		assertTrue(admin.isPresent());
	}
}