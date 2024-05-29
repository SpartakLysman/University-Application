package ua.com.foxminded.universitycms.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.com.foxminded.universitycms.model.User;
import ua.com.foxminded.universitycms.model.UserRole;
import ua.com.foxminded.universitycms.service.UserService;

@SpringBootTest(classes = { UserService.class })
public class UserServiceTest {

	@MockBean
	@Autowired
	UserService userService;

	private List<User> usersList;
	private User userTest = new User();
	private List<User> usersWithStudentRole;

	{
		User userOne = new User(1L, "John", "Akilogiun", "loggJohn", "passJohn", UserRole.ADMIN);
		User userTwo = new User(2L, "Joe", "Dibuloju", "loggJoe", "passJoe", UserRole.TEACHER);
		User userTree = new User(3L, "Gleb", "Zametalin", "loggGleb", "passGleb", UserRole.STUDENT);
		User userFour = new User(4L, "Jamila", "Ailia", "loggJamila", "passJamila", UserRole.STUDENT);
		User userFive = new User(5L, "Alex", "Asiofr", "loggAlex", "passAlex", UserRole.ADMIN);
		User userSix = new User(6L, "Artem", "Iguran", "loggArtem", "passArtem", UserRole.STUDENT);
		User userSeven = new User(7L, "Michael", "Jordan", "loggMichael", "passMichael", UserRole.TEACHER);
		User userEight = new User(8L, "Igor", "Kerokilon", "loggIgor", "passIgor", UserRole.STUDENT);
		User userNine = new User(9L, "Michel", "Toll", "loggMichel", "passMichel", UserRole.STUDENT);
		User userTen = new User(10L, "Jack", "Wolfil", "loggJack", "passJack", UserRole.TEACHER);

		usersList = List.of(userOne, userTwo, userTree, userFour, userFive, userSix, userSeven, userEight, userNine,
				userTen);

		userTest = usersList.get(3);
		usersWithStudentRole = List.of(userTree, userFour, userSix, userSeven, userNine);
	}

	@Test
	void createUserTest() {
		User user = new User(5L, "Alex", "Asiofr", "loggAlex", "passAlex", UserRole.ADMIN);

		when(userService.create(user)).thenReturn(user);

		User createdUser = userService.create(user);

		assertNotNull(createdUser);
		assertEquals(user, createdUser);

		verify(userService).create(user);
	}

	@Test
	void createAllUsersTest() {
		List<User> usersList = Arrays.asList(new User(5L, "Alex", "Asiofr", "loggAlex", "passAlex", UserRole.ADMIN),
				new User(5L, "Alex", "Asiofr", "loggAlex", "passAlex", UserRole.ADMIN));

		when(userService.createAll(usersList)).thenReturn(usersList);

		List<User> createdUsers = userService.createAll(usersList);

		assertNotNull(createdUsers);
		assertEquals(usersList.size(), createdUsers.size());

		verify(userService).createAll(usersList);
	}

	@Test
	void deleteUserTest() {
		when(userService.delete(userTest)).thenReturn(true);

		boolean isDeleted = userService.delete(userTest);

		assertEquals(isDeleted, true);

		verify(userService).delete(userTest);
	}

	@Test
	void updateUserTest() {
		User userForCheck = userTest;

		when(userService.update(userTest)).thenReturn(userTest);

		userTest = new User(15L, "Alex", "Asiofr", "loggAlex", "passAlex", UserRole.ADMIN);
		userService.update(userTest);

		assertNotEquals(userForCheck, userTest);

		verify(userService).update(userTest);
	}

	@Test
	void findUsersByNameTest() {
		when(userService.findByName(userTest.getName())).thenReturn(List.of(userTest));

		List<User> usersListByName = userService.findByName(userTest.getName());

		assertNotNull(usersListByName);
		assertEquals(usersListByName.size(), 1);
		assertEquals(usersListByName.get(0).getName(), userTest.getName());

		verify(userService).findByName(userTest.getName());
	}

	@Test
	void findUserByIdTest() {
		userTest.setId(5L);
		when(userService.findById(5L)).thenReturn(Optional.of(userTest));

		Optional<User> newUser = userService.findById(5L);

		assertEquals(newUser.get().getId(), userTest.getId());
		assertEquals(newUser.get().getName(), userTest.getName());

		verify(userService).findById(5L);
	}

	@Test
	void findUserByRoleTest() {
		when(userService.findByRole(UserRole.STUDENT)).thenReturn(usersWithStudentRole);

		List<User> newUsersWithStudentRole = userService.findByRole(UserRole.STUDENT);

		assertNotNull(newUsersWithStudentRole);
		assertEquals(newUsersWithStudentRole.size(), usersWithStudentRole.size());
		verify(userService).findByRole(UserRole.STUDENT);
	}

	@Test
	void findUserByLoginTest() {
		String login = "loggAlex";

		when(userService.findByLogin(login)).thenReturn(Optional.of(userTest));

		Optional<User> foundUser = userService.findByLogin(login);

		assertTrue(foundUser.isPresent());
		assertEquals(userTest.getLogin(), foundUser.get().getLogin());

		verify(userService).findByLogin(login);
	}

	@Test
	void findUserByLoginAndPasswordTest() {
		String login = "loggAlex";
		String password = "passAlex";

		when(userService.findByLoginAndPassword(login, password)).thenReturn(Optional.of(userTest));

		Optional<User> foundUser = userService.findByLoginAndPassword(login, password);

		assertTrue(foundUser.isPresent());
		assertEquals(userTest.getLogin(), foundUser.get().getLogin());
		assertEquals(userTest.getPassword(), foundUser.get().getPassword());

		verify(userService).findByLoginAndPassword(login, password);
	}

	@Test
	void findUserWithMaxKeyTest() {
		User userWithMaxKey = new User(10L, "Jack", "Wolfil", "loggJack", "passJack", UserRole.TEACHER);

		when(userService.findUserWithMaxKey()).thenReturn(Optional.of(userWithMaxKey));

		Optional<User> foundUser = userService.findUserWithMaxKey();

		assertTrue(foundUser.isPresent());
		assertEquals(userWithMaxKey.getId(), foundUser.get().getId());

		verify(userService).findUserWithMaxKey();
	}

	@Test
	void findAllUsersTest() {
		List<User> usersEntity = new ArrayList<>();

		for (int i = 1; i < usersList.size(); i++) {
			usersEntity.add(usersList.get(i));
		}

		when(userService.findAll()).thenReturn(usersEntity);

		List<User> newUsersEntity = userService.findAll();

		assertNotNull(usersEntity);
		assertEquals(usersEntity, newUsersEntity);
		assertEquals(usersEntity.get(0).getId(), newUsersEntity.get(0).getId());

		verify(userService).findAll();
	}
}
