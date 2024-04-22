package ua.com.foxminded.universitycms.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.service.StudentService;

@SpringBootTest(classes = { StudentService.class })
class StudentServiceTest {

	@MockBean
	@Autowired
	StudentService studentService;

	private List<Student> studentsList;
	private Student studentTest;
	private int[] size = new int[1];

	{
		Group group1 = new Group();
		group1.setId(1L);
		Student studentOne = new Student(1L, group1, "Alexey", "Bionic", "StudentOne", "5r!A54");
		Student studentTwo = new Student(2L, group1, "Dmitry", "Shwez", "StudentTwo", "f27&jvh");
		Group group5 = new Group();
		group5.setId(5L);
		Student studentThree = new Student(3L, group5, "Maxim", "Makilok", "StudentThree", "dfanS2e");
		Group group4 = new Group();
		group4.setId(4L);
		Student studentFour = new Student(4L, group4, "Dmitry", "Dolib", "StudentFoure", "Dfab29&");
		Student studentFive = new Student(5L, group5, "Potap", "Kilot", "StudentFive", "DdH3&hs");
		Student studentSix = new Student(6L, group5, "Pavel", "Dertinovskiy", "StudentSix", "@S!e4B");
		Group group3 = new Group();
		group3.setId(3L);
		Student studentSeven = new Student(7L, group3, "Artem", "Makyk", "StudentSeven", "Vr432!a");
		Group group6 = new Group();
		group6.setId(6L);
		Student studentEight = new Student(8L, group6, "Vladislav", "Uzumbiev", "StudentEight", "Uzum3!$4");
		Student studentNine = new Student(9L, group1, "Makar", "Kiminok", "StudentNine", "ffA2er1");
		Student studentTen = new Student(10L, group1, "Gleb", "Delokyz", "StudentTen", "12S!kk");
		Student studentEleven = new Student(11L, group1, "Mark", "Dolyn", "StudentEleven", "*37FFS3");
		Student studentTwelve = new Student(12L, group1, "Yuriy", "Dernisholz", "StudentTwelve", "FF2sa!!");
		Group group2 = new Group();
		group2.setId(2L);
		Student studenThirteen = new Student(13L, group2, "Ivan", "Fomin", "StudenThirteen", "Haapd3");
		Student studentFourteen = new Student(14L, group2, "Ivan", "Abroktin", "StudentFourteen", "Dc3cdj#");
		Student studentFivteen = new Student(15L, group6, "Artem", "Ernishev", "StudentFivteen", "adi&64L");
		Student studentSixteen = new Student(16L, group4, "Makhail", "Goodman", "StudentSixteen", "KK37aY");
		Student studentSeventeen = new Student(17L, group3, "Yaroslav", "Wertex", "StudentSeventeen", "YY28&a");
		Student studentEighteen = new Student(18L, group5, "Bpogdan", "Dertalok", "StudentEighteen", "2p37H#3");
		Student studentNineteen = new Student(19L, group5, "Michail", "Frukinchok", "StudentNineteen", "m81J!&");
		Student studentTwenty = new Student(20L, group4, "Nikolay", "Sertilatov", "StudentTwenty", "D72xQ&");

		studentsList = List.of(studentOne, studentTwo, studentThree, studentFour, studentFive, studentSix, studentSeven,
				studentEight, studentNine, studentTen, studentEleven, studentTwelve, studenThirteen, studentFourteen,
				studentFivteen, studentSixteen, studentSeventeen, studentEighteen, studentNineteen, studentTwenty);

		studentTest = studentsList.get(4);
		size[0] = 10;
	}

	@Test
	void createStudentTest() {

		Group groupNew = new Group();
		groupNew.setId(5L);
		Student newStudent = new Student(5L, groupNew, "Nekolay", "Ivatko", "StudentFive", "fH2*fh");

		when(studentService.create(any(Student.class))).thenReturn(newStudent);

		Student created = studentService.create(newStudent);

		assertNotNull(created);
		assertEquals(newStudent.getName(), newStudent.getName());
		assertEquals(newStudent.getSurname(), newStudent.getSurname());

		verify(studentService).create(any(Student.class));
	}

	@Test
	void createAllStudentsTest() {
		List<Student> students = new ArrayList<>();

		Group groupOne = new Group();
		groupOne.setId(6L);
		Student studentNewOne = new Student(21L, groupOne, "Vasiliy", "Manumanian", "StudentTwentyFirst", "Gj4fss&");
		Group groupTwo = new Group();
		groupTwo.setId(4L);
		Student studentNewTwo = new Student(22L, groupTwo, "Ignat", "Fidokib", "StudentTwentySecond", "LL46@!S");

		students.add(studentNewOne);
		students.add(studentNewTwo);

		when(studentService.createAll(students)).thenReturn(students);

		List<Student> newStudentsList = List.of(studentNewOne, studentNewTwo);
		List<Student> created = studentService.createAll(newStudentsList);

		assertNotNull(created);
		assertEquals(newStudentsList.get(0).getName(), studentNewOne.getName());
		assertEquals(newStudentsList.get(1).getName(), studentNewTwo.getName());

		verify(studentService).createAll(newStudentsList);
	}

	@Test
	void deleteStudentTest() {
		when(studentService.delete(studentTest)).thenReturn(true);

		boolean isDeleted = studentService.delete(studentTest);

		assertEquals(isDeleted, true);

		verify(studentService).delete(studentTest);
	}

	@Test
	void updateStudentTest() {
		Student studentForCheck = studentTest;

		when(studentService.update(studentTest)).thenReturn(studentTest);

		Group groupOne = new Group();
		groupOne.setId(5L);
		studentTest = new Student(50L, groupOne, "Anton", "Gorodnuk", "StudentFifty", "gHs29*&");

		studentService.update(studentTest);

		assertNotEquals(studentForCheck, studentTest);

		verify(studentService).update(studentTest);
	}

	@Test
	void findStudentsByNameTest() {
		when(studentService.findByName(studentTest.getName())).thenReturn(List.of(studentTest));

		List<Student> studentsListByTitle = studentService.findByName(studentTest.getName());

		assertNotNull(studentsListByTitle);
		assertEquals(studentsListByTitle.size(), 1);
		assertEquals(studentsListByTitle.get(0).getName(), studentTest.getName());

		verify(studentService).findByName(studentTest.getName());
	}

	@Test
	void findStudentByIdTest() {
		when(studentService.findById(5L)).thenReturn(Optional.of(studentTest));

		Optional<Student> newStudent = studentService.findById(5L);

		assertEquals(newStudent.get().getId(), studentTest.getId());
		assertEquals(newStudent.get().getName(), studentTest.getName());

		verify(studentService).findById(5L);
	}

	@Test
	void findStudentByLoginTest() {
		String login = "StudentFive";

		when(studentService.findByLogin(login)).thenReturn(Optional.of(studentTest));

		Optional<Student> foundStudent = studentService.findByLogin(login);

		assertTrue(foundStudent.isPresent());
		assertEquals(studentTest.getLogin(), foundStudent.get().getLogin());

		verify(studentService).findByLogin(login);
	}

	@Test
	void findStudentByLoginAndPasswordTest() {
		String login = "StudentFive";
		String password = "DdH3&hs";

		when(studentService.findByLoginAndPassword(login, password)).thenReturn(Optional.of(studentTest));

		Optional<Student> foundStudent = studentService.findByLoginAndPassword(login, password);

		assertTrue(foundStudent.isPresent());
		assertEquals(studentTest.getLogin(), foundStudent.get().getLogin());
		assertEquals(studentTest.getPassword(), foundStudent.get().getPassword());

		verify(studentService).findByLoginAndPassword(login, password);
	}

	@Test
	void findStudentWithMaxKeyTest() {
		Student studentWithMaxKey = new Student(20L, new Group(), "Nikolay", "Sertilatov", "StudentTwenty", "D72xQ&");

		when(studentService.findStudentWithMaxKey()).thenReturn(Optional.of(studentWithMaxKey));

		Optional<Student> foundStudent = studentService.findStudentWithMaxKey();

		assertTrue(foundStudent.isPresent());
		assertEquals(studentWithMaxKey.getId(), foundStudent.get().getId());

		verify(studentService).findStudentWithMaxKey();
	}

	@Test
	void findAllStudentsTest() {
		List<Student> studentsEntity = new ArrayList<>();

		for (int i = 1; i < studentsList.size(); i++) {
			studentsEntity.add(studentsList.get(i));
		}

		when(studentService.findAll()).thenReturn(studentsEntity);

		List<Student> newStudentsEntity = studentService.findAll();

		assertNotNull(studentsEntity);
		assertEquals(studentsEntity, newStudentsEntity);
		assertEquals(studentsEntity.get(0).getId(), newStudentsEntity.get(0).getId());

		verify(studentService).findAll();
	}
}
