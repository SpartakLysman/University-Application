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

import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.service.AdminService;

@SpringBootTest(classes = { AdminService.class })
public class AdminServiceTest {

	@MockBean
	@Autowired
	AdminService adminService;

	private List<Admin> adminsList;
	private Admin adminTest = new Admin();

	{
		Admin adminOne = new Admin(1L, "Alex", "Saridin", "loggAlex", "passAlex");
		Admin adminTwo = new Admin(2L, "Anton", "Karibin", "loggAnton", "passAnton");
		Admin adminTree = new Admin(3L, "Gleb", "Iviok", "loggGleb", "passGleb");
		Admin adminFour = new Admin(4L, "Jamila", "Ililia", "loggJamila", "passJamila");
		Admin adminFive = new Admin(4L, "Johnattan", "Joe", "loggJoe", "passJoe");
		Admin adminSix = new Admin(6L, "John", "Lotijyh", "loggJohn", "passJohn");
		Admin adminSeven = new Admin(7L, "Michael", "Jordan", "loggMichael", "passMichael");
		Admin adminEight = new Admin(8L, "Igor", "Kerokilon", "loggIgor", "passIgor");
		Admin adminNine = new Admin(9L, "Michel", "Toll", "loggMichel", "passMichel");
		Admin adminTen = new Admin(10L, "Jack", "Wolfil", "loggJack", "passJack");

		adminsList = List.of(adminOne, adminTwo, adminTree, adminFour, adminFive, adminSix, adminSeven, adminEight,
				adminNine, adminTen);

		adminTest = adminsList.get(3);
	}

	@Test
	void createAdminTest() {
		Admin admin = new Admin(1L, "Alex", "Saridin", "logg", "pass");

		when(adminService.create(admin)).thenReturn(admin);

		Admin createdAdmin = adminService.create(admin);

		assertNotNull(createdAdmin);
		assertEquals(admin, createdAdmin);

		verify(adminService).create(admin);
	}

	@Test
	void createAllAdminsTest() {
		List<Admin> adminsList = Arrays.asList(new Admin(1L, "Alex", "Saridin", "loggAlex", "passAlex"),
				new Admin(2L, "Anton", "Karibin", "loggAnton", "passAnton"));

		when(adminService.createAll(adminsList)).thenReturn(adminsList);

		List<Admin> createdAdmins = adminService.createAll(adminsList);

		assertNotNull(createdAdmins);
		assertEquals(adminsList.size(), createdAdmins.size());

		verify(adminService).createAll(adminsList);
	}

	@Test
	void deleteAdminTest() {
		when(adminService.delete(adminTest)).thenReturn(true);

		boolean isDeleted = adminService.delete(adminTest);

		assertEquals(isDeleted, true);

		verify(adminService).delete(adminTest);
	}

	@Test
	void updateAdminTest() {
		Admin adminForChek = adminTest;

		when(adminService.update(adminTest)).thenReturn(adminTest);

		adminTest = new Admin(40L, "Jamila", "Ililia", "loggJamila", "passJamila");
		adminService.update(adminTest);

		assertNotEquals(adminForChek, adminTest);

		verify(adminService).update(adminTest);
	}

	@Test
	void findAdminsByNameTest() {
		when(adminService.findByName(adminTest.getName())).thenReturn(List.of(adminTest));

		List<Admin> adminListByName = adminService.findByName(adminTest.getName());

		assertNotNull(adminListByName);
		assertEquals(adminListByName.size(), 1);
		assertEquals(adminListByName.get(0).getName(), adminTest.getName());

		verify(adminService).findByName(adminTest.getName());
	}

	@Test
	void findAdminByIdTest() {
		adminTest.setId(5L);
		when(adminService.findById(5L)).thenReturn(Optional.of(adminTest));

		Optional<Admin> newUser = adminService.findById(5L);

		assertEquals(newUser.get().getId(), adminTest.getId());
		assertEquals(newUser.get().getName(), adminTest.getName());

		verify(adminService).findById(5L);
	}

	@Test
	void findAdminByLoginTest() {
		String login = "loggJamila";

		when(adminService.findByLogin(login)).thenReturn(Optional.of(adminTest));

		Optional<Admin> foundAdmin = adminService.findByLogin(login);

		assertTrue(foundAdmin.isPresent());
		assertEquals(adminTest.getLogin(), foundAdmin.get().getLogin());

		verify(adminService).findByLogin(login);
	}

	@Test
	void findAdminByLoginAndPasswordTest() {
		String login = "loggJamila";
		String password = "passJamila";

		when(adminService.findByLoginAndPassword(login, password)).thenReturn(Optional.of(adminTest));

		Optional<Admin> foundAdmin = adminService.findByLoginAndPassword(login, password);

		assertTrue(foundAdmin.isPresent());
		assertEquals(adminTest.getLogin(), foundAdmin.get().getLogin());
		assertEquals(adminTest.getPassword(), foundAdmin.get().getPassword());

		verify(adminService).findByLoginAndPassword(login, password);
	}

	@Test
	void findAdminWithMaxKeyTest() {
		Admin adminWithMaxKey = new Admin(10L, "Jack", "Wolfil", "loggJack", "passJack");

		when(adminService.findAdminWithMaxKey()).thenReturn(Optional.of(adminWithMaxKey));

		Optional<Admin> foundAdmin = adminService.findAdminWithMaxKey();

		assertTrue(foundAdmin.isPresent());
		assertEquals(adminWithMaxKey.getId(), foundAdmin.get().getId());

		verify(adminService).findAdminWithMaxKey();
	}

	@Test
	void findAllAdminsTest() {
		List<Admin> adminsEntity = new ArrayList<>();

		for (int i = 1; i < adminsList.size(); i++) {
			adminsEntity.add(adminsList.get(i));
		}

		when(adminService.findAll()).thenReturn(adminsEntity);

		List<Admin> newCoursesEntity = adminService.findAll();

		assertNotNull(adminsEntity);
		assertEquals(adminsEntity, newCoursesEntity);
		assertEquals(adminsEntity.get(0).getId(), newCoursesEntity.get(0).getId());

		verify(adminService).findAll();
	}
}
