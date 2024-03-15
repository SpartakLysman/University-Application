package ua.com.foxminded.universitycms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.repository.GroupRepository;
import ua.com.foxminded.universitycms.util.LoggingController;

@Service
public class GroupService {

	private final GroupRepository groupRepository;
	public final static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	public GroupService(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	public Group create(Group group) {
		LOGGER.debug("Group creating - " + group.toString());

		Group newGroup = groupRepository.save(group);
		LOGGER.info("Group was successfully created with id - " + group.getId());

		return newGroup;
	}

	public List<Group> createAll(List<Group> groupsList) {
		LOGGER.debug("All groups creating...");

		List<Group> newGroups = groupRepository.saveAll(groupsList);
		LOGGER.info("All groups were successfully created " + groupsList.toString());

		return newGroups;
	}

	public boolean delete(Group group) {
		LOGGER.debug("Group deleting - " + group.toString());
		boolean deleted = groupRepository.deleteGroup(group);
		LOGGER.info("Group was successfully removed with id - " + group.getId());

		return deleted;
	}

	public Group update(Group group) {
		LOGGER.debug("Group updating - " + group.toString());

		Group newGroup = groupRepository.save(group);
		LOGGER.info("Group was successfully updated with id - " + group.getId());

		return newGroup;
	}

	public List<Group> findByTitle(String title) {
		LOGGER.debug("Group finding by title");
		List<Group> groupsList = groupRepository.findByTitle(title);
		LOGGER.info("Groups were successfully found by title - " + title);

		return groupsList;
	}

	public Optional<Group> findById(long key) {
		LOGGER.debug("Group findind by id");
		Optional<Group> group = groupRepository.findById(key);
		LOGGER.info("Group was successfully found by id - " + key);

		return group;
	}

	public List<Group> findAll() {
		LOGGER.debug("All groups findind...");
		List<Group> groupsList = groupRepository.findAll();
		LOGGER.info("All groups were successfully found ");

		return groupsList;
	}
}
