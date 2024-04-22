package ua.com.foxminded.universitycms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.universitycms.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	List<Teacher> findByName(String name);

	Optional<Teacher> findByLogin(String login);

	Optional<Teacher> findByLoginAndPassword(String login, String password);

	Optional<Teacher> findFirstByOrderByIdDesc();
}
