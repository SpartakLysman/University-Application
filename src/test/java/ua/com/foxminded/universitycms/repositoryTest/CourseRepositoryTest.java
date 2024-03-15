package ua.com.foxminded.universitycms.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ua.com.foxminded.universitycms.model.Course;
import ua.com.foxminded.universitycms.repository.CourseRepository;

@SpringBootTest(classes = { CourseRepository.class })
public class CourseRepositoryTest {

	@Mock
	private CourseRepository courseRepository;

	@Test
	public void testDeleteCourse() {
		Course courseToDelete = new Course();

		when(courseRepository.deleteCourse(courseToDelete)).thenReturn(true);

		boolean result = courseRepository.deleteCourse(courseToDelete);

		verify(courseRepository).deleteCourse(courseToDelete);
		assertTrue(result);
	}

	@Test
	public void testFindByTitle() {
		String title = "Math";

		when(courseRepository.findByTitle(title)).thenReturn(Arrays.asList(new Course()));

		List<Course> courses = courseRepository.findByTitle(title);

		verify(courseRepository).findByTitle(title);
		assertNotNull(courses);
		assertFalse(courses.isEmpty());
	}
}
