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

import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.repository.TeacherRepository;

@SpringBootTest(classes = { TeacherRepository.class })
public class TeacherRepositoryTest {

	@Mock
	private TeacherRepository teacherRepository;

	@Test
	public void testFindByName() {
		String name = "Jane Smith";

		when(teacherRepository.findByName(name)).thenReturn(Arrays.asList(new Teacher()));

		List<Teacher> teachers = teacherRepository.findByName(name);

		verify(teacherRepository).findByName(name);
		assertNotNull(teachers);
		assertFalse(teachers.isEmpty());
	}

	@Test
	public void testFindByLogin() {
		String login = "jane_smith";

		when(teacherRepository.findByLogin(login)).thenReturn(Optional.of(new Teacher()));

		Optional<Teacher> teacher = teacherRepository.findByLogin(login);

		verify(teacherRepository).findByLogin(login);
		assertTrue(teacher.isPresent());
	}

	@Test
	public void testFindByLoginAndPassword() {
		String login = "jane_smith";
		String password = "password123";

		when(teacherRepository.findByLoginAndPassword(login, password)).thenReturn(Optional.of(new Teacher()));

		Optional<Teacher> teacher = teacherRepository.findByLoginAndPassword(login, password);

		verify(teacherRepository).findByLoginAndPassword(login, password);
		assertTrue(teacher.isPresent());
	}

	@Test
	public void testFindFirstByOrderByKeyDesc() {
		when(teacherRepository.findFirstByOrderByIdDesc()).thenReturn(Optional.of(new Teacher()));

		Optional<Teacher> teacher = teacherRepository.findFirstByOrderByIdDesc();

		verify(teacherRepository).findFirstByOrderByIdDesc();
		assertTrue(teacher.isPresent());
	}
}
