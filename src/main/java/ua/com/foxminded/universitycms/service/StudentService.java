package ua.com.foxminded.universitycms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ua.com.foxminded.universitycms.model.Course;
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.repository.CourseRepository;
import ua.com.foxminded.universitycms.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	private final CourseRepository courseRepository;

	private final static Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}

	public Student create(Student student) {
		LOGGER.debug("Student creating... ");
		Student newStudent = studentRepository.save(student);
		LOGGER.info("Student was successfully created: " + student.toString());

		return newStudent;
	}

	public List<Student> createAll(List<Student> studentsList) {
		LOGGER.debug("Students creating... ");
		List<Student> newStudents = studentRepository.saveAll(studentsList);
		LOGGER.info("All students were successfully created: " + studentsList.toString());

		return newStudents;
	}

	@Transactional
	public boolean enrollStudentInCourse(Student student, long courseId) {
		try {
			Optional<Course> courseOptional = courseRepository.findById(courseId);
			if (courseOptional.isEmpty()) {
				LOGGER.error("Course not found");
				return false;
			}
			Course course = courseOptional.get();

			if (course.getStudents().contains(student)) {
				LOGGER.warn("Student is already enrolled in the course");
				return false;
			}

			Optional<Student> studentOptional = studentRepository.findById(student.getId());
			if (studentOptional.isEmpty()) {
				LOGGER.error("Student not found");
				return false;
			}
			Student managedStudent = studentOptional.get();

			course.addStudent(managedStudent);
			courseRepository.save(course);

			LOGGER.info("Student added to the course successfully");
			return true;
		} catch (Exception e) {
			LOGGER.error("Error adding student to the course ", e);
			return false;
		}
	}

	@Transactional
	public boolean deleteStudentFromCourse(Student student, long courseId) {
		try {
			Optional<Course> courseOptional = courseRepository.findById(courseId);
			if (courseOptional.isEmpty()) {
				LOGGER.error("Course not found");
				return false;
			}
			Course course = courseOptional.get();

			Optional<Student> studentOptional = studentRepository.findById(student.getId());
			if (studentOptional.isEmpty()) {
				LOGGER.error("Student not found");
				return false;
			}
			Student managedStudent = studentOptional.get();

			course.deleteStudent(managedStudent);
			courseRepository.save(course);

			LOGGER.info("Student removed from the course successfully");
			return true;
		} catch (Exception e) {
			LOGGER.error("Error removing student from the course ", e);
			return false;
		}
	}

	public boolean delete(Student student) {
		LOGGER.debug("Student deleting... " + student.toString());
		boolean deleted = studentRepository.deleteStudent(student);
		LOGGER.info("Student was successfully deleted with id - " + student.getId());

		return deleted;
	}

	public void deleteById(Long id) {
		LOGGER.debug("Student with id deleteng... ");
		studentRepository.deleteById(id);
		LOGGER.info("Student with id- " + id + " was successfully deleted");
	}

	public Student update(Student student) {
		LOGGER.debug("Student updating... " + student.toString());
		Student newStudent = studentRepository.save(student);
		LOGGER.info("Student was successfully updated with id - " + student.getId());

		return newStudent;
	}

	public List<Student> findByName(String name) {
		LOGGER.debug("Students finding by name... ");
		List<Student> studentsList = studentRepository.findByName(name);
		LOGGER.info("Students were successfully found by name - " + name);

		return studentsList;
	}

	public List<Student> findByGroupId(Long groupId) {
		LOGGER.debug("Student finding by group id... ");
		List<Student> students = studentRepository.findByGroupId(groupId);
		LOGGER.info("Student was successfully found by group id - " + groupId);

		return students;
	}

	public Optional<Student> findById(long id) {
		LOGGER.debug("Student finding by id... ");
		Optional<Student> student = studentRepository.findById(id);
		LOGGER.info("Student was successfully found by id - " + id);

		return student;
	}

	public Optional<Student> findByLogin(String login) {
		LOGGER.debug("Student finding by login... ");
		Optional<Student> student = studentRepository.findByLogin(login);
		LOGGER.info("Student was successfully founded by login - " + login);

		return student;
	}

	public Optional<Student> findByLoginAndPassword(String login, String password) {
		LOGGER.debug("Student finding by login and password... ");
		Optional<Student> student = studentRepository.findByLoginAndPassword(login, password);
		LOGGER.info("Student was successfully founded by login and password - " + login);

		return student;
	}

	public Optional<Student> findStudentWithMaxKey() {
		LOGGER.debug("Student with max key findind... ");
		Optional<Student> studentWithMaxKey = studentRepository.findFirstByOrderByIdDesc();
		LOGGER.info("Student with max key was found");

		return studentWithMaxKey;
	}

	public List<Student> findAll() {
		LOGGER.debug("All students findind... ");
		List<Student> studentsList = studentRepository.findAll();
		LOGGER.info("All students were successfully found");

		return studentsList;
	}

}
