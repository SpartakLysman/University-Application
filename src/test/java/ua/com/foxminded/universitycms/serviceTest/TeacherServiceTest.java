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

import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.service.TeacherService;

@SpringBootTest(classes = { TeacherService.class })
public class TeacherServiceTest {

	@MockBean
	@Autowired
	TeacherService teacherService;

	private List<Teacher> teachersList;
	private Teacher teacherTest = new Teacher();

	{
		Teacher teacherOne = new Teacher(1L, "Jamile", "Anifuo", "loggJamile", "passJamile");
		Teacher teacherTwo = new Teacher(2L, "Dmitriy", "Punoluk", "loggDmitriy", "passDmitriy");
		Teacher teacherTree = new Teacher(3L, "Emily", "Johnson", "loggEmily", "passEmily");
		Teacher teacherFour = new Teacher(4L, "Jacob", "Smith", "loggJacob", "passJacob");
		Teacher teacherFive = new Teacher(5L, "Sophia", "Williams", "loggSophia", "passSophia");
		Teacher teacherSix = new Teacher(6L, "Michael", "Davis", "loggMichael", "passMichael");
		Teacher teacherSeven = new Teacher(7L, "Olivia", "Martinez", "loggOlivia", "passOlivia");
		Teacher teacherEight = new Teacher(8L, "Ethan", "Brown", "loggEthan", "passEthan");
		Teacher teacherNine = new Teacher(9L, "Ava", "Jones", "loggAva", "passAva");
		Teacher teacherTen = new Teacher(10L, "William", "Taylor", "loggWilliam", "passWilliam");

		teachersList = List.of(teacherOne, teacherTwo, teacherTree, teacherFour, teacherFive, teacherSix, teacherSeven,
				teacherEight, teacherNine, teacherTen);

		teacherTest = teachersList.get(4);
	}

	@Test
	void createTeacherTest() {
		Teacher teacher = new Teacher(5L, "Sophia", "Williams", "loggSophia", "passSophia");

		when(teacherService.create(teacher)).thenReturn(teacher);

		Teacher createdTeacher = teacherService.create(teacher);

		assertNotNull(createdTeacher);
		assertEquals(teacher, createdTeacher);

		verify(teacherService).create(teacher);
	}

	@Test
	void createAllTeachersTest() {
		List<Teacher> teachersList = Arrays.asList(new Teacher(5L, "Sophia", "Williams", "loggSophia", "passSophia"),
				new Teacher(6L, "Michael", "Davis", "loggMichael", "passMichael"));

		when(teacherService.createAll(teachersList)).thenReturn(teachersList);

		List<Teacher> createdTeachers = teacherService.createAll(teachersList);

		assertNotNull(createdTeachers);
		assertEquals(teachersList.size(), createdTeachers.size());

		verify(teacherService).createAll(teachersList);
	}

	@Test
	void deleteTeacherTest() {
		when(teacherService.delete(teacherTest)).thenReturn(true);

		boolean isDeleted = teacherService.delete(teacherTest);

		assertEquals(isDeleted, true);

		verify(teacherService).delete(teacherTest);
	}

	@Test
	void updateTeacherTest() {
		Teacher teacherForCheck = teacherTest;

		when(teacherService.update(teacherTest)).thenReturn(teacherTest);

		teacherTest = new Teacher(50L, "Sophia", "Williams", "loggSophia", "passSophia");
		teacherService.update(teacherTest);

		assertNotEquals(teacherForCheck, teacherTest);

		verify(teacherService).update(teacherTest);
	}

	@Test
	void findTeachersByNameTest() {
		when(teacherService.findByName(teacherTest.getName())).thenReturn(List.of(teacherTest));

		List<Teacher> teachersListByTitle = teacherService.findByName(teacherTest.getName());

		assertNotNull(teachersListByTitle);
		assertEquals(teachersListByTitle.size(), 1);
		assertEquals(teachersListByTitle.get(0).getName(), teacherTest.getName());

		verify(teacherService).findByName(teacherTest.getName());
	}

	@Test
	void findTeacherByIdTest() {
		teacherTest.setId(5L);
		when(teacherService.findById(5L)).thenReturn(Optional.of(teacherTest));

		Optional<Teacher> newTeacher = teacherService.findById(5L);

		assertEquals(newTeacher.get().getId(), teacherTest.getId());
		assertEquals(newTeacher.get().getName(), teacherTest.getName());

		verify(teacherService).findById(5L);
	}

	@Test
	void findTeacherByLoginTest() {
		String login = "loggSophia";

		when(teacherService.findByLogin(login)).thenReturn(Optional.of(teacherTest));

		Optional<Teacher> foundTeacher = teacherService.findByLogin(login);

		assertTrue(foundTeacher.isPresent());
		assertEquals(teacherTest.getLogin(), foundTeacher.get().getLogin());

		verify(teacherService).findByLogin(login);
	}

	@Test
	void findTeacherByLoginAndPasswordTest() {
		String login = "loggSophia";
		String password = "passSophia";

		when(teacherService.findByLoginAndPassword(login, password)).thenReturn(Optional.of(teacherTest));

		Optional<Teacher> foundTeacher = teacherService.findByLoginAndPassword(login, password);

		assertTrue(foundTeacher.isPresent());
		assertEquals(teacherTest.getLogin(), foundTeacher.get().getLogin());
		assertEquals(teacherTest.getPassword(), foundTeacher.get().getPassword());

		verify(teacherService).findByLoginAndPassword(login, password);
	}

	@Test
	void findTeacherWithMaxKeyTest() {
		Teacher teacherWithMaxKey = new Teacher(10L, "William", "Taylor", "loggWilliam", "passWilliam");

		when(teacherService.findTeacherWithMaxKey()).thenReturn(Optional.of(teacherWithMaxKey));

		Optional<Teacher> foundTeacher = teacherService.findTeacherWithMaxKey();

		assertTrue(foundTeacher.isPresent());
		assertEquals(teacherWithMaxKey.getId(), foundTeacher.get().getId());

		verify(teacherService).findTeacherWithMaxKey();
	}

	@Test
	void findAllTeachersTest() {
		List<Teacher> teachersEntity = new ArrayList<>();

		for (int i = 1; i < teachersList.size(); i++) {
			teachersEntity.add(teachersList.get(i));
		}

		when(teacherService.findAll()).thenReturn(teachersEntity);

		List<Teacher> newCoursesEntity = teacherService.findAll();

		assertNotNull(teachersEntity);
		assertEquals(teachersEntity, newCoursesEntity);
		assertEquals(teachersEntity.get(0).getId(), newCoursesEntity.get(0).getId());

		verify(teacherService).findAll();
	}
}
