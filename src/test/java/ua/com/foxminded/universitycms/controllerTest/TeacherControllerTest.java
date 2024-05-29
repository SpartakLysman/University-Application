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

import ua.com.foxminded.universitycms.controller.TeacherController;
import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.repository.TeacherRepository;
import ua.com.foxminded.universitycms.service.CourseService;
import ua.com.foxminded.universitycms.service.TeacherService;

@RunWith(MockitoJUnitRunner.class)
public class TeacherControllerTest {

	@InjectMocks
	private TeacherController teacherController;

	@Mock
	private TeacherService teacherService;

	@Mock
	private TeacherRepository teacherRepository;

	@Mock
	private CourseService courseService;

	@Mock
	private Model model;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testShowTeachers() {
		List<Teacher> teachers = new ArrayList<>();
		teachers.add(new Teacher(1L, "John", "Doe", "loggJohn", "pass"));
		teachers.add(new Teacher(2L, "Jane", "Smith", "loggJane", "passJane"));
		when(teacherRepository.findAll()).thenReturn(teachers);

		String result = teacherController.showTeachers(model);

		assertEquals("teacherList", result);
		verify(model, times(1)).addAttribute("teachers", teachers);
	}

	@Test
	public void testShowCreateTeacherForm() {
		Model model = mock(Model.class);
		Teacher teacher = new Teacher();
		teacher.setId(30L);

		String result = teacherController.showCreateTeacherForm(model);

		assertEquals("createTeacher", result);
		verify(model, times(1)).addAttribute(eq("teacher"), any(Teacher.class));
	}

	@Test
	public void testCreateTeacher() {
		Teacher teacher = new Teacher(1L, "John", "Doe", "loggJohn", "pass");

		String result = teacherController.createTeacher(teacher);

		assertEquals("redirect:/teachers", result);
		verify(teacherService, times(1)).create(teacher);
	}

	@Test
	void testUpdateTeacher() {
		Long teacherId = 1L;
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		teacher.setName("John Doe");

		String result = teacherController.updateTeacher(teacher);

		assertEquals("redirect:/teachers", result);
		verify(teacherService, times(1)).update(teacher);
	}

	@Test
	void testShowUpdateTeacherForm() {
		Long teacherId = 1L;
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		Optional<Teacher> optionalTeacher = Optional.of(teacher);
		Model model = mock(Model.class);
		when(teacherService.findById(teacherId)).thenReturn(optionalTeacher);

		String result = teacherController.showUpdateTeacherForm(teacherId, model);

		assertEquals("updateTeacher", result);
		verify(model, times(1)).addAttribute("teacher", teacher);
	}

	@Test
	public void testDeleteTeacher() {
		Long id = 1L;

		String result = teacherController.deleteTeacher(id);

		assertEquals("redirect:/teachers", result);
		verify(teacherService, times(1)).deleteById(id);
	}

	@Test
	public void testShowDeleteTeacherConfirmation() {
		Teacher teacher = new Teacher(1L, "John", "Doe", "loggJohn", "pass");

		when(teacherService.findById(1L)).thenReturn(Optional.of(teacher));

		String result = teacherController.showDeleteTeacherConfirmation(1L, model);

		assertEquals("deleteTeacher", result);
		verify(model, times(1)).addAttribute("teacher", teacher);
	}

	@Test
	public void testGetTeacherById() {
		Teacher teacher = new Teacher(1L, "John", "Doe", "loggJohn", "pass");

		when(teacherService.findById(1L)).thenReturn(Optional.of(teacher));

		String result = teacherController.getTeacherById(1L, model);

		assertEquals("getTeacherById", result);
		verify(model, times(1)).addAttribute("teacher", teacher);
	}
}
