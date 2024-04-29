package ua.com.foxminded.universitycms.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.com.foxminded.universitycms.model.Course;
import ua.com.foxminded.universitycms.repository.CourseRepository;
import ua.com.foxminded.universitycms.service.CourseService;

@SpringBootTest(classes = { CourseService.class })
class CourseServiceTest {

	@MockBean
	@Autowired
	CourseService courseService;

	@Mock
	private CourseRepository courseRepository;

	private List<Course> coursesList;
	private Course courseTest;
	private int[] size = new int[1];

	{
		Course courseOne = new Course(1L, "Biology", "Animals");
		Course courseTwo = new Course(2L, "Math", "Derivatives");
		Course courseTree = new Course(3L, "Geography", "Japan");
		Course courseFour = new Course(4L, "Geography", "Ukraine");
		Course courseFive = new Course(5L, "Programing", "Java");
		Course courseSix = new Course(6L, "Computer Science", "CPU");
		Course courseSeven = new Course(7L, "Programing", "C++");
		Course courseEight = new Course(8L, "Programing", "Phyton");
		Course courseNine = new Course(9L, "Geography", "Black Sea");
		Course courseTen = new Course(10L, "English", "Present Continues");

		coursesList = List.of(courseOne, courseTwo, courseTree, courseFour, courseFive, courseSix, courseSeven,
				courseEight, courseNine, courseTen);

		courseTest = coursesList.get(4);
		size[0] = 10;
	}

	@Test
	void createCourseTest() {
		Course course = new Course(5L, "Math", "Algebra");

		when(courseService.create(course)).thenReturn(course);

		Course createdCourse = courseService.create(course);

		assertNotNull(createdCourse);
		assertEquals(course, createdCourse);

		verify(courseService).create(course);
	}

	@Test
	void createAllCoursesTest() {
		List<Course> coursesList = Arrays.asList(new Course(5L, "Math", "Algebra"),
				new Course(6L, "Programing", "Java"));

		when(courseService.createAll(coursesList)).thenReturn(coursesList);

		List<Course> createdCourses = courseService.createAll(coursesList);

		assertNotNull(createdCourses);
		assertEquals(coursesList.size(), createdCourses.size());

		verify(courseService).createAll(coursesList);
	}

	@Test
	void deleteCourseTest() {
		when(courseService.delete(courseTest)).thenReturn(true);

		Course courseOne = new Course(1L, "Biology", "Animals");
		Course courseTwo = new Course(2L, "Math", "Derivatives");
		Course courseTree = new Course(3L, "Geography", "Japan");
		Course courseFour = new Course(4L, "Geography", "Ukraine");

		Course courseSix = new Course(6L, "Computer Science", "CPU");
		Course courseSeven = new Course(7L, "Programing", "C++");
		Course courseEight = new Course(8L, "Programing", "Phyton");
		Course courseNine = new Course(9L, "Geography", "Black Sea");
		Course courseTen = new Course(10L, "English", "Present Continues");

		List<Course> newCoursesList = List.of(courseOne, courseTwo, courseTree, courseFour, courseSix, courseSeven,
				courseEight, courseNine, courseTen);
		boolean isDeleted = courseService.delete(courseTest);

		assertEquals(isDeleted, true);
		assertEquals(newCoursesList.size(), (coursesList.size() - 1));

		verify(courseService).delete(courseTest);
	}

//	@Test
//	void testDeleteById() {
//		Course courseOne = new Course(123L, "Biology", "Animals");
//
//		// When
//		courseService.deleteById(courseOne.getId());
//
//		// Then
//		verify(courseRepository, times(1)).deleteById(courseOne.getId());
//	}

	@Test
	void updateCourseTest() {
		Course courseForCheck = courseTest;

		when(courseService.update(courseTest)).thenReturn(courseTest);

		courseTest = new Course(50L, "Swimming", "Fast");
		courseService.update(courseTest);

		assertNotEquals(courseForCheck, courseTest);

		verify(courseService).update(courseTest);
	}

	@Test
	void findCoursesByTitleTest() {
		when(courseService.findByTitle(courseTest.getTitle())).thenReturn(List.of(courseTest));

		List<Course> coursesListByTitle = courseService.findByTitle(courseTest.getTitle());

		assertNotNull(coursesListByTitle);
		assertEquals(coursesListByTitle.size(), 1);
		assertEquals(coursesListByTitle.get(0).getTitle(), courseTest.getTitle());

		verify(courseService).findByTitle(courseTest.getTitle());
	}

	@Test
	void findCoursesByIdTest() {
		when(courseService.findById(courseTest.getId())).thenReturn(Optional.of(coursesList.get(4)));

		Optional<Course> newCourse = courseService.findById(courseTest.getId());

		assertEquals(newCourse.get().getTitle(), courseTest.getTitle());
		assertEquals(newCourse.get().getDescription(), courseTest.getDescription());

		verify(courseService).findById(courseTest.getId());
	}

	@Test
	void findAllCoursesTest() {
		List<Course> coursesEntity = new ArrayList<>();

		for (int i = 1; i < coursesList.size(); i++) {
			coursesEntity.add(coursesList.get(i));
		}

		when(courseService.findAll()).thenReturn(coursesEntity);

		List<Course> newCoursesEntity = courseService.findAll();

		assertNotNull(coursesEntity);
		assertEquals(coursesEntity, newCoursesEntity);
		assertEquals(coursesEntity.get(0).getId(), newCoursesEntity.get(0).getId());

		verify(courseService).findAll();
	}
}
