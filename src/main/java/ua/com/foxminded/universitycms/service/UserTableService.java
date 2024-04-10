package ua.com.foxminded.universitycms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.universitycms.model.UserRole;
import ua.com.foxminded.universitycms.model.UserTable;
import ua.com.foxminded.universitycms.repository.UserTableRepository;

@Service
public class UserTableService {

	private final UserTableRepository userTableRepository;

	private final static Logger LOGGER = LoggerFactory.getLogger(UserTableService.class);

	@Autowired
	public UserTableService(UserTableRepository userTableRepository) {
		this.userTableRepository = userTableRepository;
	}

	public UserTable create(UserTable user) {
		LOGGER.debug("User creating... ");
		UserTable newUser = userTableRepository.save(user);
		LOGGER.info("User was successfully created: " + user.toString());

		return newUser;
	}

	public List<UserTable> createAll(List<UserTable> usersList) {
		LOGGER.debug("Users creating... ");
		List<UserTable> newUsers = userTableRepository.saveAll(usersList);
		LOGGER.info("All users were successfully created: " + usersList.toString());

		return newUsers;
	}

//	@Transactional
//	public boolean addStudentToCourse(UserTable student, long courseId) {
//		try {
//			Optional<Course> courseOptional = courseRepository.findById(courseId);
//			Optional<Student> studentOptional = studentRepository.findById(student.getId());
//
//			if (courseOptional.isPresent() && studentOptional.isPresent()) {
//				Course course = courseOptional.get();
//				Student updatedStudent = studentOptional.get();
//
//				course.addStudent(updatedStudent);
//				courseRepository.save(course);
//
//				LOGGER.info("User added to the course successfully.");
//				return true;
//			} else {
//				LOGGER.error("Course or user not found.");
//				return false;
//			}
//		} catch (Exception e) {
//			LOGGER.error("Error adding user to the course.", e);
//			return false;
//		}
//	}

//	@Transactional
//	public boolean deleteStudentFromCourse(UserTable userTable, long courseId) {
//		try {
//			Optional<Course> courseOptional = courseRepository.findById(courseId);
//			Optional<Student> studentOptional = studentRepository.findById(student.getId());
//
//			if (courseOptional.isPresent() && studentOptional.isPresent()) {
//				Course course = courseOptional.get();
//				Student updatedStudent = studentOptional.get();
//
//				course.deleteStudent(updatedStudent);
//				courseRepository.save(course);
//
//				LOGGER.info("User removed from the course successfully.");
//				return true;
//			} else {
//				LOGGER.error("Course or user not found.");
//				return false;
//			}
//		} catch (Exception e) {
//			LOGGER.error("Error removing user from the course.", e);
//			return false;
//		}
//	}

	public boolean delete(UserTable user) {
		LOGGER.debug("User deleting... " + user.toString());
		try {
			userTableRepository.delete(user);
			LOGGER.info("User was successfully deleted with id - " + user.getId());
			return true;
		} catch (Exception e) {
			LOGGER.error("Failed to delete user with id - " + user.getId(), e);
			return false;
		}
	}

	public UserTable update(UserTable user) {
		LOGGER.debug("User updating... " + user.toString());
		UserTable newUser = userTableRepository.save(user);
		LOGGER.info("User was successfully updated with id - " + user.getId());

		return newUser;
	}

	public List<UserTable> findByName(UserTable name) {
		LOGGER.debug("Users findind by name... ");
		List<UserTable> usersList = userTableRepository.findByName(name);
		LOGGER.info("Users were successfully found by name - " + name);

		return usersList;
	}

	public Optional<UserTable> findStudentWithMaxKey() {
		LOGGER.debug("The latest user id findind... ");
		Optional<UserTable> latestId = userTableRepository.findFirstByOrderByIdDesc();
		LOGGER.info("The latest user id was found");

		return latestId;
	}

	public Optional<UserTable> findById(long id) {
		LOGGER.debug("User finding by id... ");
		Optional<UserTable> user = userTableRepository.findById(id);
		LOGGER.info("User was successfully found by id - " + id);

		return user;
	}

	public List<UserTable> findByRole(UserRole role) {
		return userTableRepository.findByRole(role);
	}

	public List<UserTable> findAll() {
		LOGGER.debug("All users findind... ");
		List<UserTable> studentsList = userTableRepository.findAll();
		LOGGER.info("All users were successfully found");

		return studentsList;
	}
}
