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

	public List<Admin> createAll(List<Admin> adminsList) {
		LOGGER.debug("Admins creating... ");
		List<Admin> newAdmins = adminRepository.saveAll(adminsList);
		LOGGER.info("All admins were successfully created: " + adminsList.toString());

		return newAdmins;
	}

	public boolean delete(Admin admin) {
		LOGGER.debug("Admin deleting... " + admin.toString());
		try {
			adminRepository.delete(admin);
			LOGGER.info("Admin was successfully deleted with id - " + admin.getId());
			return true;
		} catch (Exception e) {
			LOGGER.error("Failed to delete admin with id - " + admin.getId(), e);
			return false;
		}
	}

	public void deleteById(Long id) {
		LOGGER.debug("Admin with id deleteng... ");
		adminRepository.deleteById(id);
		LOGGER.info("Admin with id- " + id + " was successfully deleted");
	}

	public Admin update(Admin user) {
		LOGGER.debug("Admin updating... " + user.toString());
		Admin newAdmin = adminRepository.save(user);
		LOGGER.info("Admin was successfully updated with id - " + user.getId());

		return newAdmin;
	}

	public List<Admin> findByName(String name) {
		LOGGER.debug("Admins finding by name... ");
		List<Admin> adminsList = adminRepository.findByName(name);
		LOGGER.info("Admins were successfully found by name - " + name);

		return adminsList;
	}

	public Optional<Admin> findById(long id) {
		LOGGER.debug("Admin finding by id... ");
		Optional<Admin> admin = adminRepository.findById(id);
		LOGGER.info("Admin was successfully found by id - " + id);

		return admin;
	}

	public Optional<Admin> findByLogin(String login) {
		LOGGER.debug("Admin finding by login... ");
		Optional<Admin> admin = adminRepository.findByLogin(login);
		LOGGER.info("Admin was successfully found by login - " + login);

		return admin;
	}

	public Optional<Admin> findByLoginAndPassword(String login, String password) {
		LOGGER.debug("Admin finding by login and password... ");
		Optional<Admin> admin = adminRepository.findByLoginAndPassword(login, password);
		LOGGER.info("Admin was successfully founded by login and password - " + login);

		return admin;
	}

	public Optional<Admin> findAdminWithMaxKey() {
		LOGGER.debug("Admin with max key findind... ");
		Optional<Admin> adminWithMaxKey = adminRepository.findFirstByOrderByIdDesc();
		LOGGER.info("Admin with max key was found- " + adminWithMaxKey.toString());

		return adminWithMaxKey;
	}

	public List<Admin> findAll() {
		LOGGER.debug("All admins findind... ");
		List<Admin> adminsList = adminRepository.findAll();
		LOGGER.info("All admins were successfully found");

		return adminsList;
	}

}
