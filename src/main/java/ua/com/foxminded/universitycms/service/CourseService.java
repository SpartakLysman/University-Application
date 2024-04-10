package ua.com.foxminded.universitycms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ua.com.foxminded.universitycms.model.Course;
import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.repository.CourseRepository;
import ua.com.foxminded.universitycms.repository.GroupRepository;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	private final GroupRepository groupRepository;

	private final static Logger LOGGER = LoggerFactory.getLogger(GroupService.class);

	@Autowired
	public CourseService(CourseRepository courseRepository, GroupRepository groupRepository) {
		this.courseRepository = courseRepository;
		this.groupRepository = groupRepository;
	}

	public Course create(Course course) {
		LOGGER.debug("Course creating... " + course.toString());
		Course newCourse = courseRepository.save(course);
		LOGGER.info("Course was created successfully with id - " + course.getId());

		return newCourse;
	}

	public List<Course> createAll(List<Course> coursesList) {
		LOGGER.debug("All courses creating... ");
		List<Course> newCourses = courseRepository.saveAll(coursesList);
		LOGGER.info("All courses were successfully created - " + coursesList.toString());

		return newCourses;
	}

	@Transactional
	public boolean addCourseToGroup(Course course, long groupId) {
		boolean isGroupExist = groupRepository.findById(groupId).isPresent();
		boolean isCourseExist = courseRepository.findById(course.getId()).isPresent();

		if (isGroupExist && isCourseExist) {
			Optional<Group> groupOptional = groupRepository.findById(groupId);
			if (groupOptional.isPresent()) {
				Group group1 = groupOptional.get();
				course.addGroup(group1);
				courseRepository.save(course);
			} else {
				System.out.println("Group not found");
			}
		} else {
			System.out.println("Some problems");
		}
		return true;
	}

	@Transactional
	public boolean deleteCourseFromGroup(Course course, long groupId) {
		boolean isGroupExist = groupRepository.findById(groupId).isPresent();
		boolean isCourseExist = courseRepository.findById(course.getId()).isPresent();

		if (isGroupExist && isCourseExist) {
			Optional<Group> groupOptional = groupRepository.findById(groupId);
			if (groupOptional.isPresent()) {
				course.deleteGroup(groupOptional.get());
				courseRepository.save(course);
			} else {
				System.out.println("Group not found");
			}
		} else {
			System.out.println("Some problems");
		}
		return true;
	}

	public boolean delete(Course course) {
		LOGGER.debug("Course deleting... " + course.toString());
		boolean deleted = courseRepository.deleteCourse(course);
		LOGGER.info("Course was successfully deleted with id - " + course.getId());

		return deleted;
	}

	public Course update(Course course) {
		LOGGER.debug("Course updating... " + course.toString());
		Course newCourse = courseRepository.save(course);
		LOGGER.info("Course was successfully updated with id - " + course.getId());

		return newCourse;
	}

	public List<Course> findByTitle(String title) {
		LOGGER.debug("Courses finding by title... ");
		List<Course> coursesList = courseRepository.findByTitle(title);
		LOGGER.info("Courses were successfully found by title - " + title);

		return coursesList;
	}

	public Optional<Course> findById(long id) {
		LOGGER.debug("Course finding... " + id);
		Optional<Course> course = courseRepository.findById(id);
		LOGGER.info("Course was successfully found by id - " + id);

		return course;
	}

	public List<Course> findAll() {
		LOGGER.debug("All courses finding... ");
		List<Course> coursesList = courseRepository.findAll();
		LOGGER.info("All courses were successfully found");

		return coursesList;
	}
}
