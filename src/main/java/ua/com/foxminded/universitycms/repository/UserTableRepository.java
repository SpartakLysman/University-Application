package ua.com.foxminded.universitycms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.universitycms.model.UserRole;
import ua.com.foxminded.universitycms.model.UserTable;

@Repository
public interface UserTableRepository extends JpaRepository<UserTable, Long> {

	Optional<UserTable> save(Optional<UserTable> user);

	List<UserTable> findByName(UserTable name);

	List<UserTable> findByRole(UserRole role);

	Optional<UserTable> findByLogin(String login);

	Optional<UserTable> findByLoginAndPassword(String login, String password);

	Optional<UserTable> findFirstByOrderByIdDesc();
}