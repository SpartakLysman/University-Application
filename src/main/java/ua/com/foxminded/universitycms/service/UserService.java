package ua.com.foxminded.universitycms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.universitycms.model.User;
import ua.com.foxminded.universitycms.model.UserRole;
import ua.com.foxminded.universitycms.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User create(User user) {
		LOGGER.debug("User creating... ");
		User newUser = userRepository.save(user);
		LOGGER.info("User was successfully created: " + user.toString());

		return newUser;
	}

	public List<User> createAll(List<User> usersList) {
		LOGGER.debug("Users creating... ");
		List<User> newUsers = userRepository.saveAll(usersList);
		LOGGER.info("All users were successfully created: " + usersList.toString());

		return newUsers;
	}

	public boolean delete(User user) {
		LOGGER.debug("User deleting... " + user.toString());
		try {
			userRepository.delete(user);
			LOGGER.info("User was successfully deleted with id - " + user.getId());
			return true;
		} catch (Exception e) {
			LOGGER.error("Failed to delete user with id - " + user.getId(), e);
			return false;
		}
	}

	public void deleteById(Long id) {
		LOGGER.debug("User with id deleteng... ");
		userRepository.deleteById(id);
		LOGGER.info("User with id- " + id + " was successfully deleted");
	}

	public User update(User user) {
		LOGGER.debug("User updating... " + user.toString());
		User newUser = userRepository.save(user);
		LOGGER.info("User was successfully updated with id - " + user.getId());

		return newUser;
	}

	public List<User> findByName(String name) {
		LOGGER.debug("Users findind by name... ");
		List<User> usersList = userRepository.findByName(name);
		LOGGER.info("Users were successfully found by name - " + name);

		return usersList;
	}

	public Optional<User> findById(long id) {
		LOGGER.debug("User finding by id... ");
		Optional<User> user = userRepository.findById(id);
		LOGGER.info("User was successfully found by id - " + id);

		return user;
	}

	public List<User> findByRole(UserRole role) {
		LOGGER.debug("Users finding by role... ");
		List<User> usersWithRole = userRepository.findByRole(role);
		LOGGER.info("Users were successfully found by role - " + role);

		return usersWithRole;
	}

	public Optional<User> findByLogin(String login) {
		LOGGER.debug("User finding by login... ");
		Optional<User> user = userRepository.findByLogin(login);
		LOGGER.info("User was successfully found by login - " + login);

		return user;
	}

	public Optional<User> findByLoginAndPassword(String login, String password) {
		LOGGER.debug("User finding by login and password... ");
		Optional<User> user = userRepository.findByLoginAndPassword(login, password);
		LOGGER.info("User was successfully found by login and password - " + login);

		return user;
	}

	public Optional<User> findUserWithMaxKey() {
		LOGGER.debug("User with max key id findind... ");
		Optional<User> userWithMaxKey = userRepository.findFirstByOrderByIdDesc();
		LOGGER.info("User with max key was found");

		return userWithMaxKey;
	}

	public List<User> findAll() {
		LOGGER.debug("All users findind... ");
		List<User> studentsList = userRepository.findAll();
		LOGGER.info("All users were successfully found");

		return studentsList;
	}
}
