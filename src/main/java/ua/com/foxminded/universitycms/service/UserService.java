package ua.com.foxminded.universitycms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.universitycms.model.User;
import ua.com.foxminded.universitycms.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final static Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User create(User user) {
		LOGGER.debug("User creating...");
		User newUser = userRepository.save(user);
		LOGGER.info("User was successfully created" + user.toString());

		return newUser;
	}

	public List<User> findAll() {
		LOGGER.debug("All users findind...");
		List<User> usersList = userRepository.findAll();
		LOGGER.info("All users were successfully found");

		return usersList;
	}

	public void delete(long userId) {
		LOGGER.debug("User deleting with id- " + userId);
		userRepository.deleteById(userId);
		LOGGER.info("User was successfully deleted with id - " + userId);
	}

	public User update(User user) {
		LOGGER.debug("User updating - " + user.toString());
		User newUser = userRepository.save(user);
		LOGGER.info("User was successfully updated with id - " + user.getId());

		return newUser;
	}

	public Optional<User> findById(long id) {
		LOGGER.debug("User finding by id");
		Optional<User> user = userRepository.findById(id);
		LOGGER.info("User was successfully found by id - " + id);

		return user;
	}
}
