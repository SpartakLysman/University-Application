package ua.com.foxminded.universitycms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.universitycms.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	List<Admin> findByName(String name);

	Optional<Admin> findByLogin(String login);

	Optional<Admin> findByLoginAndPassword(String login, String password);

	Optional<Admin> findFirstByOrderByIdDesc();

}
