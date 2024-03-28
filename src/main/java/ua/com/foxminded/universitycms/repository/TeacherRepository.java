package ua.com.foxminded.universitycms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.model.User;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByLogin(String login);

}
