package ua.com.foxminded.universitycms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.repository.AdminRepository;

@Service
public class AdminService {

	private final AdminRepository adminRepository;

	private final static Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	public Admin create(Admin admin) {
		LOGGER.debug("Admin creating... ");
		Admin newAdmin = adminRepository.save(admin);
		LOGGER.info("Admin was successfully created " + admin.toString());

		return newAdmin;
	}

	public void delete(long userId) {
		LOGGER.debug("Admin deleting with id... " + userId);
		adminRepository.deleteById(userId);
		LOGGER.info("Admin was successfully deleted with id - " + userId);
	}

	public Admin update(Admin user) {
		LOGGER.debug("Admin updating... " + user.toString());
		Admin newAdmin = adminRepository.save(user);
		LOGGER.info("Admin was successfully updated with id - " + user.getId());

		return newAdmin;
	}

	public Optional<Admin> findById(long id) {
		LOGGER.debug("Admin finding by id... ");
		Optional<Admin> user = adminRepository.findById(id);
		LOGGER.info("Admin was successfully found by id - " + id);

		return user;
	}

	public List<Admin> findAll() {
		LOGGER.debug("All admins findind... ");
		List<Admin> usersList = adminRepository.findAll();
		LOGGER.info("All admins were successfully found");

		return usersList;
	}
}
