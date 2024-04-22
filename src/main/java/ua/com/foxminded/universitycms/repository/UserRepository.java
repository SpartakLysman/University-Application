package ua.com.foxminded.universitycms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.universitycms.model.User;
import ua.com.foxminded.universitycms.model.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> save(Optional<User> user);

	List<User> findByName(String name);

	List<User> findByRole(UserRole role);

	Optional<User> findByLogin(String login);

	Optional<User> findByLoginAndPassword(String login, String password);

	Optional<User> findFirstByOrderByIdDesc();
}