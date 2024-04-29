package ua.com.foxminded.universitycms.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import ua.com.foxminded.universitycms.controller.StudentController;
import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.repository.StudentRepository;
import ua.com.foxminded.universitycms.service.StudentService;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

	@InjectMocks
	private StudentController studentController;

	@Mock
	private StudentService studentService;

	@Mock
	private StudentRepository studentRepository;

	@Mock
	private Model model;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testShowStudents() {
		Group groupNew1 = new Group(1L, "Test Group");
		Group groupNew2 = new Group(2L, "Test Group");

		List<Student> students = new ArrayList<>();
		students.add(new Student(1L, groupNew1, "Alex", "Ivanov", "loggAlex", "passAlex"));
		students.add(new Student(2L, groupNew2, "Jamila", "Aliila", "loggJamila", "passJamila"));

		when(studentRepository.findAll()).thenReturn(students);

		Model model = mock(Model.class);
		String result = studentController.showStudents(model);

		assertEquals("studentList", result);
		verify(model, times(1)).addAttribute("students", students);
	}

	@Test
	public void testCreateStudent() {
		Group groupNew2 = new Group(1L, "Test Group");
		Student student = new Student(2L, groupNew2, "Jamila", "Aliila", "loggJamila", "passJamila");

		String result = studentController.createStudent(student);

		assertEquals("redirect:/students", result);
		verify(studentService, times(1)).create(student);
	}

	@Test
	public void testShowCreateStudentForm() {
		Student student = new Student();
		student.setId(1L); // Set the id property

		String result = studentController.showCreateStudentForm(model);

		assertEquals("createStudent", result);
		verify(model, times(1)).addAttribute("student", student);
	}

	@Test
	public void testUpdateStudent() {
		Long studentId = 1L;
		Student student = new Student();
		student.setId(studentId);
		student.setName("Jamila");

		when(studentService.update(student)).thenReturn(student);

		String viewName = studentController.updateStudent(studentId, student);

		assertEquals("redirect:/students/update/" + studentId, viewName);

		verify(studentService, times(1)).update(student);
	}

	@Test
	public void testDeleteStudent() {
		String result = studentController.deleteStudent(1L);

		assertEquals("redirect:/students", result);
		verify(studentService, times(1)).deleteById(1L);
	}

	@Test
	public void testShowDeleteStudentConfirmation() {
		Group groupNew2 = new Group(1L, "Test Group");
		Student student = new Student(2L, groupNew2, "Jamila", "Aliila", "loggJamila", "passJamila");

		when(studentService.findById(1L)).thenReturn(Optional.of(student));

		String result = studentController.showDeleteStudentConfirmation(1L, model);

		assertEquals("deleteStudent", result);
		verify(model, times(1)).addAttribute("student", student);
	}

	@Test
	public void testShowDeleteStudentConfirmation_WhenStudentNotFound() {
		when(studentService.findById(1L)).thenReturn(Optional.empty());

		String result = studentController.showDeleteStudentConfirmation(1L, model);

		assertEquals("redirect:/students", result);
	}

	@Test
	public void testGetStudentById() {
		Group groupNew2 = new Group(1L, "Test Group");
		Student student = new Student(2L, groupNew2, "Jamila", "Aliila", "loggJamila", "passJamila");

		when(studentService.findById(1L)).thenReturn(Optional.of(student));

		String result = studentController.getStudentById(1L, model);

		assertEquals("getStudentById", result);
		verify(model, times(1)).addAttribute("student", student);
	}

	@Test
	public void testGetStudentById_WhenStudentNotFound() {
		when(studentService.findById(1L)).thenReturn(Optional.empty());

		String result = studentController.getStudentById(1L, model);

		assertEquals("error", result);
	}
}
