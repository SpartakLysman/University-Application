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

import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.repository.StudentRepository;

@SpringBootTest(classes = { StudentRepository.class })
public class StudentRepositoryTest {

	@Mock
	private StudentRepository studentRepository;

	@Test
	public void testDeleteStudent() {
		Student studentToDelete = new Student();

		when(studentRepository.deleteStudent(studentToDelete)).thenReturn(true);

		boolean result = studentRepository.deleteStudent(studentToDelete);

		verify(studentRepository).deleteStudent(studentToDelete);
		assertEquals(true, result);
	}

	@Test
	public void testFindByName() {
		String name = "John Doe";

		when(studentRepository.findByName(name)).thenReturn(Arrays.asList(new Student()));

		List<Student> students = studentRepository.findByName(name);

		verify(studentRepository).findByName(name);
		assertNotNull(students);
		assertFalse(students.isEmpty());
	}

	@Test
	public void testFindByLogin() {
		String login = "john_doe";

		when(studentRepository.findByLogin(login)).thenReturn(Optional.of(new Student()));

		Optional<Student> student = studentRepository.findByLogin(login);

		assertTrue(student.isPresent());

		verify(studentRepository).findByLogin(login);

	}

	@Test
	public void testFindByLoginAndPassword() {
		String login = "john_doe";
		String password = "password123";

		when(studentRepository.findByLoginAndPassword(login, password)).thenReturn(Optional.of(new Student()));

		Optional<Student> student = studentRepository.findByLoginAndPassword(login, password);

		verify(studentRepository).findByLoginAndPassword(login, password);
		assertTrue(student.isPresent());
	}

	@Test
	public void testFindFirstByOrderByKeyDesc() {
		when(studentRepository.findFirstByOrderByIdDesc()).thenReturn(Optional.of(new Student()));

		Optional<Student> student = studentRepository.findFirstByOrderByIdDesc();

		verify(studentRepository).findFirstByOrderByIdDesc();
		assertTrue(student.isPresent());
	}
}
