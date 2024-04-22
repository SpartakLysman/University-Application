package ua.com.foxminded.universitycms.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import ua.com.foxminded.universitycms.model.User;
import ua.com.foxminded.universitycms.model.UserRole;
import ua.com.foxminded.universitycms.repository.UserRepository;

@SpringBootTest(classes = { UserRepository.class })
public class UserRepositoryTest {

	@Mock
	private UserRepository userRepository;

	@Test
	public void testSave() {
		Optional<User> userToSave = Optional.of(new User());

		when(userRepository.save(userToSave)).thenReturn(userToSave);

		Optional<User> savedUser = userRepository.save(userToSave);

		verify(userRepository).save(userToSave);
		assertEquals(userToSave, savedUser);
	}

	@Test
	public void testFindByName() {
		String name = "John Doe";

		when(userRepository.findByName(name)).thenReturn(Arrays.asList(new User()));

		List<User> users = userRepository.findByName(name);

		verify(userRepository).findByName(name);
		assertNotNull(users);
		assertFalse(users.isEmpty());
	}

	@Test
	public void testFindByRole() {
		UserRole role = UserRole.ADMIN;

		when(userRepository.findByRole(role)).thenReturn(Arrays.asList(new User()));

		List<User> users = userRepository.findByRole(role);

		verify(userRepository).findByRole(role);
		assertNotNull(users);
		assertFalse(users.isEmpty());
	}

	@Test
	public void testFindByLogin() {
		String login = "john_doe";

		when(userRepository.findByLogin(login)).thenReturn(Optional.of(new User()));

		Optional<User> user = userRepository.findByLogin(login);

		verify(userRepository).findByLogin(login);
		assertTrue(user.isPresent());
	}

	@Test
	public void testFindByLoginAndPassword() {
		String login = "john_doe";
		String password = "password123";

		when(userRepository.findByLoginAndPassword(login, password)).thenReturn(Optional.of(new User()));

		Optional<User> user = userRepository.findByLoginAndPassword(login, password);

		verify(userRepository).findByLoginAndPassword(login, password);
		assertTrue(user.isPresent());
	}

	@Test
	public void testFindFirstByOrderByKeyDesc() {
		when(userRepository.findFirstByOrderByIdDesc()).thenReturn(Optional.of(new User()));

		Optional<User> user = userRepository.findFirstByOrderByIdDesc();

		verify(userRepository).findFirstByOrderByIdDesc();
		assertTrue(user.isPresent());
	}
}
