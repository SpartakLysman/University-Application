package ua.com.foxminded.universitycms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.repository.GroupRepository;
import ua.com.foxminded.universitycms.repository.StudentRepository;

@Service
public class GroupService {

	private final GroupRepository groupRepository;

	private final StudentRepository studentRepository;

	public final static Logger LOGGER = LoggerFactory.getLogger(GroupService.class);

	@Autowired
	public GroupService(GroupRepository groupRepository, StudentRepository studentRepository) {
		this.groupRepository = groupRepository;
		this.studentRepository = studentRepository;
	}

	public Group create(Group group) {
		LOGGER.debug("Group creating... " + group.toString());
		Group newGroup = groupRepository.save(group);
		LOGGER.info("Group was successfully created with id - " + group.getId());

		return newGroup;
	}

	public List<Group> createAll(List<Group> groupsList) {
		LOGGER.debug("All groups creating... ");
		List<Group> newGroups = groupRepository.saveAll(groupsList);
		LOGGER.info("All groups were successfully created " + groupsList.toString());

		return newGroups;
	}

	public boolean delete(Group group) {
		LOGGER.debug("Group deleting... " + group.toString());
		boolean deleted = groupRepository.deleteGroup(group);
		LOGGER.info("Group was successfully removed with id - " + group.getId());

		return deleted;
	}

	public void deleteById(Long id) {
		LOGGER.debug("Group with id deleteng... ");
		groupRepository.deleteById(id);
		LOGGER.info("Group with id- " + id + " was successfully deleted");
	}

	public Group update(Group group) {
		LOGGER.debug("Group updating... " + group.toString());

		Group newGroup = groupRepository.save(group);
		LOGGER.info("Group was successfully updated with id - " + group.getId());

		return newGroup;
	}

	public List<Group> findByTitle(String title) {
		LOGGER.debug("Groups finding by title... ");
		List<Group> groupsList = groupRepository.findByTitle(title);
		LOGGER.info("Groups were successfully found by title - " + title);

		return groupsList;
	}

	public Optional<Group> findById(long id) {
		LOGGER.debug("Group findind by id... ");
		Optional<Group> group = groupRepository.findById(id);
		LOGGER.info("Group was successfully found by id - " + id);

		return group;
	}

	public List<Group> findAll() {
		LOGGER.debug("All groups findind... ");
		List<Group> groupsList = groupRepository.findAll();
		LOGGER.info("All groups were successfully found ");

		return groupsList;
	}

	public void assignStudent(Long groupId, Long studentId) {
		Optional<Group> optionalGroup = groupRepository.findById(groupId);
		Optional<Student> optionalStudent = studentRepository.findById(studentId);

		if (optionalGroup.isPresent() && optionalStudent.isPresent()) {
			Group group = optionalGroup.get();
			Student student = optionalStudent.get();

			if (!group.getStudents().contains(student)) {
				group.addStudent(student);
				groupRepository.save(group);
			} else {
				throw new EntityExistsException("Student is already assigned to the group");
			}
		} else {
			throw new EntityNotFoundException("Group or Student not found");
		}
	}

	public void removeStudent(Long groupId, Long studentId) {
		Optional<Group> optionalGroup = groupRepository.findById(groupId);
		Optional<Student> optionalStudent = studentRepository.findById(studentId);

		if (optionalGroup.isPresent() && optionalStudent.isPresent()) {
			Group group = optionalGroup.get();
			Student student = optionalStudent.get();

			if (group.getStudents().contains(student)) {
				group.removeStudent(student);
				groupRepository.save(group);
			} else {
				throw new EntityNotFoundException("Student is not assigned to the group");
			}
		} else {
			throw new EntityNotFoundException("Group or Student not found");
		}
	}
}
