package ua.com.foxminded.universitycms.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import ua.com.foxminded.universitycms.controller.CourseController;
import ua.com.foxminded.universitycms.model.Course;
import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.repository.CourseRepository;
import ua.com.foxminded.universitycms.service.CourseService;
import ua.com.foxminded.universitycms.service.GroupService;
import ua.com.foxminded.universitycms.service.TeacherService;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {

	@InjectMocks
	private CourseController courseController;

	@Mock
	private CourseRepository courseRepository;

	@Mock
	private CourseService courseService;

	@Mock
	private TeacherService teacherService;

	@Mock
	private GroupService groupService;

	@Mock
	private Model model;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testShowCourses() {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course(1L, "Math", "Mathematics course"));
		courses.add(new Course(2L, "Physics", "Physics course"));

		when(courseRepository.findAll()).thenReturn(courses);

		Model model = mock(Model.class);
		String viewName = courseController.showCourses(model);

		verify(courseRepository, times(1)).findAll();
		verify(model, times(1)).addAttribute("courses", courses);

		assertEquals("courseList", viewName);
	}

	@Test
	public void testCreateCourse() {
		Course course = new Course();
		course.setId(1L);
		course.setTitle("Math");
		course.setDescription("Mathematics course");

		String result = courseController.createCourse(course);

		assertEquals("redirect:/courses", result);
		verify(courseService, times(1)).create(course);
	}

	@Test
	public void testShowCreateCourseForm() {
		Model model = mock(Model.class);
		String result = courseController.showCreateCourseForm(model);

		assertEquals("createCourse", result);
		verify(model, times(1)).addAttribute(eq("course"), any(Course.class));
	}

	@Test
	void testUpdateCourse() {
		Long courseId = 1L;
		Course course = new Course();
		course.setId(courseId);
		course.setTitle("Math");
		course.setDescription("Mathematics course");

		when(courseService.update(course)).thenReturn(course);

		String result = courseController.updateCourse(course);

		assertEquals("redirect:/courses", result);
		verify(courseService, times(1)).update(course);
	}

	@Test
	void testShowUpdateCourseForm() {
		Long courseId = 1L;
		Course course = new Course();
		course.setId(courseId);
		Optional<Course> optionalCourse = Optional.of(course);
		Model model = mock(Model.class);
		when(courseService.findById(courseId)).thenReturn(optionalCourse);

		String result = courseController.showUpdateCourseForm(courseId, model);

		assertEquals("updateCourse", result);
		verify(model, times(1)).addAttribute("course", course);
	}

	@Test
	public void testDeleteCourse() {
		Long courseId = 1L;
		String result = courseController.deleteCourse(courseId);

		assertEquals("redirect:/courses", result);
		verify(courseService, times(1)).deleteById(courseId);
	}

	@Test
	public void testShowDeleteCourseConfirmation() {
		Long courseId = 1L;
		Course course = new Course();
		course.setId(courseId);
		course.setTitle("Math");
		course.setDescription("Mathematics course");

		Optional<Course> optionalCourse = Optional.of(course);

		when(courseService.findById(courseId)).thenReturn(optionalCourse);

		String result = courseController.showDeleteCourseConfirmation(courseId, model);

		assertEquals("deleteCourse", result);
		verify(model, times(1)).addAttribute("course", course);
	}

	@Test
	public void testGetCourseById_WhenCourseExists() {
		Long courseId = 1L;
		Course course = new Course();
		course.setId(courseId);
		course.setTitle("Math");
		course.setDescription("Mathematics course");

		Optional<Course> optionalCourse = Optional.of(course);

		when(courseService.findById(courseId)).thenReturn(optionalCourse);

		String result = courseController.getCourseById(courseId, model);

		assertEquals("getCourseById", result);
		verify(model, times(1)).addAttribute("course", course);
	}

	@Test
	public void testGetCourseById_WhenCourseDoesNotExist() {
		Long courseId = 1L;
		Optional<Course> optionalCourse = Optional.empty();

		when(courseService.findById(courseId)).thenReturn(optionalCourse);

		String result = courseController.getCourseById(courseId, model);

		assertEquals("error", result);
	}

	@Test
	public void testAssignTeacherToCourse() {
		Long courseId = 1L;
		Long teacherId = 1L;
		String result = courseController.assignTeacherToCourse(courseId, teacherId);

		assertEquals("redirect:/courses", result);
		verify(courseService, times(1)).assignTeacher(courseId, teacherId);
	}

	@Test
	public void testShowAssignGroupsForm_WhenCourseExists() {
		Long courseId = 1L;
		Course course = new Course();
		course.setId(courseId);
		course.setTitle("Math");
		course.setDescription("Mathematics course");

		List<Group> groups = new ArrayList<>();

		Optional<Course> optionalCourse = Optional.of(course);
		when(courseService.findById(courseId)).thenReturn(optionalCourse);
		when(groupService.findAll()).thenReturn(groups);

		String result = courseController.showAssignGroupForm(courseId, model);

		assertEquals("assignGroup", result);
		verify(model, times(1)).addAttribute("course", course);
		verify(model, times(1)).addAttribute("groups", groups);
	}

	@Test
	public void testShowAssignGroupsForm_WhenCourseDoesNotExist() {
		Long courseId = 1L;
		Optional<Course> optionalCourse = Optional.empty();

		when(courseService.findById(courseId)).thenReturn(optionalCourse);

		String result = courseController.showAssignGroupForm(courseId, model);

		assertEquals("error", result);
	}

	@Test
	public void testAssignGroupsToCourse() {
		Long courseId = 1L;
		List<Long> groupIds = new ArrayList<>();

		String result = courseController.assignGroupToCourse(courseId, groupIds);

		assertEquals("redirect:/courses", result);
		verify(courseService, times(1)).assignGroup(courseId, groupIds);
	}
}
