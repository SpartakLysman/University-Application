package ua.com.foxminded.universitycms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.repository.TeacherRepository;

@Service
public class TeacherService {

	private final TeacherRepository teacherRepository;

	private final static Logger LOGGER = LoggerFactory.getLogger(TeacherService.class);

	@Autowired
	public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	public Teacher create(Teacher teacher) {
		LOGGER.debug("Teacher creating... ");
		Teacher newTeacher = teacherRepository.save(teacher);
		LOGGER.info("Teacher was successfully created: " + teacher.toString());

		return newTeacher;
	}

	public List<Teacher> createAll(List<Teacher> teachersList) {
		LOGGER.debug("Teachers creating... ");
		List<Teacher> newTeachers = teacherRepository.saveAll(teachersList);
		LOGGER.info("All teachers were successfully created: " + teachersList.toString());

		return newTeachers;
	}

	public boolean delete(Teacher teacher) {
		LOGGER.debug("Teacher deleting... " + teacher.toString());
		try {
			teacherRepository.delete(teacher);
			LOGGER.info("Teacher was successfully deleted with id - " + teacher.getId());
			return true;
		} catch (Exception e) {
			LOGGER.error("Failed to delete teacher with id - " + teacher.getId(), e);
			return false;
		}
	}

	public Teacher update(Teacher teacher) {
		LOGGER.debug("Teacher updating... " + teacher.toString());
		Teacher newStudent = teacherRepository.save(teacher);
		LOGGER.info("Teacher was successfully updated with id - " + teacher.getId());

		return newStudent;
	}

	public List<Teacher> findByName(String name) {
		LOGGER.debug("Teachers finding by name... ");
		List<Teacher> teachersList = teacherRepository.findByName(name);
		LOGGER.info("Teachers were successfully found by name - " + name);

		return teachersList;
	}

	public Optional<Teacher> findById(long id) {
		LOGGER.debug("Teacher finding by id... ");
		Optional<Teacher> teacher = teacherRepository.findById(id);
		LOGGER.info("Teacher was successfully found by id - " + id);

		return teacher;
	}

	public Optional<Teacher> findByLogin(String login) {
		LOGGER.debug("Teacher finding by login... ");
		Optional<Teacher> teacher = teacherRepository.findByLogin(login);
		LOGGER.info("Teacher was successfully founded by login - " + login);

		return teacher;
	}

	public Optional<Teacher> findByLoginAndPassword(String login, String password) {
		LOGGER.debug("Teacher finding by login and password... ");
		Optional<Teacher> teacher = teacherRepository.findByLoginAndPassword(login, password);
		LOGGER.info("Teacher was successfully founded by login and password - " + login);

		return teacher;
	}

	public Optional<Teacher> findTeacherWithMaxKey() {
		LOGGER.debug("Teacher with max key findind... ");
		Optional<Teacher> teacherWithMaxKey = teacherRepository.findFirstByOrderByIdDesc();
		LOGGER.info("Teacher with max key was found");

		return teacherWithMaxKey;
	}

	public List<Teacher> findAll() {
		LOGGER.debug("All teachers findind... ");
		List<Teacher> teachersList = teacherRepository.findAll();
		LOGGER.info("All teachers were successfully found");

		return teachersList;
	}
}
