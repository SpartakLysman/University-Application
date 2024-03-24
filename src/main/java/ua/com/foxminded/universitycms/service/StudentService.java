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
		LOGGER.debug("Student creating...");

		Student newStudent = studentRepository.save(student);
		LOGGER.info("Students was successfully created" + student.toString());

		return newStudent;
	}

	public List<Student> createAll(List<Student> studentsList) {
		LOGGER.debug("Student creating...");

		List<Student> newStudents = studentRepository.saveAll(studentsList);
		LOGGER.info("All students were successfully created" + studentsList.toString());

		return newStudents;
	}

	@Transactional
	public boolean addStudentToCourse(Student student, long courseId) {
		try {
			Optional<Course> courseOptional = courseRepository.findById(courseId);
			Optional<Student> studentOptional = studentRepository.findById(student.getId());

			if (courseOptional.isPresent() && studentOptional.isPresent()) {
				Course course = courseOptional.get();
				Student updatedStudent = studentOptional.get();

				course.addStudent(updatedStudent);
				courseRepository.save(course);

				LOGGER.info("Student added to the course successfully.");
				return true;
			} else {
				LOGGER.error("Course or student not found.");
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("Error adding student to the course.", e);
			return false;
		}
	}

	@Transactional
	public boolean deleteStudentFromCourse(Student student, long courseId) {
		try {
			Optional<Course> courseOptional = courseRepository.findById(courseId);
			Optional<Student> studentOptional = studentRepository.findById(student.getId());

			if (courseOptional.isPresent() && studentOptional.isPresent()) {
				Course course = courseOptional.get();
				Student updatedStudent = studentOptional.get();

				// Remove the student from the course and save the changes
				course.deleteStudent(updatedStudent);
				courseRepository.save(course);

				LOGGER.info("Student removed from the course successfully.");
				return true;
			} else {
				LOGGER.error("Course or student not found.");
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("Error removing student from the course.", e);
			return false;
		}
	}

	public boolean delete(Student student) {
		LOGGER.debug("Student deleting - " + student.toString());

		boolean deleted = studentRepository.deleteStudent(student);
		LOGGER.info("Student was successfully deleted with id - " + student.getId());

		return deleted;
	}

	public Student update(Student student) {
		LOGGER.debug("Student updating - " + student.toString());

		Student newStudent = studentRepository.save(student);
		LOGGER.info("Student was successfully updated with id - " + student.getId());

		return newStudent;
	}

	public List<Student> findByName(String name) {
		LOGGER.debug("Student findind by name");
		List<Student> studentsList = studentRepository.findByName(name);
		LOGGER.info("Students were successfully found by name - " + name);

		return studentsList;
	}

	public Optional<Student> findStudentWithMaxKey() {
		LOGGER.debug("The latest student id findind...");
		Optional<Student> latestId = studentRepository.findFirstByOrderByIdDesc();
		LOGGER.info("The latest student id was found");

		return latestId;
	}

	public Optional<Student> findById(long key) {

		LOGGER.debug("Student finding by id");
		Optional<Student> student = studentRepository.findById(key);
		LOGGER.info("Student was successfully found by id - " + key);

		return student;
	}

	public List<Student> findAll() {
		LOGGER.debug("All student findind...");
		List<Student> studentsList = studentRepository.findAll();
		LOGGER.info("All students were successfully found");

		return studentsList;
	}
}
