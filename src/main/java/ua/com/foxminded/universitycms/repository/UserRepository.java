package ua.com.foxminded.universitycms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.universitycms.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> save(Optional<User> user);
}