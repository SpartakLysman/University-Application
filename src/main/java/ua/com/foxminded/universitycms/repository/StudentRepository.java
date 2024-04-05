package ua.com.foxminded.universitycms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.universitycms.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByLogin(String login);

	Optional<Student> findByLoginAndPassword(String login, String password);

	@Modifying
	@Query("DELETE FROM Student s WHERE s = :student")
	boolean deleteStudent(@Param("student") Student student);

	Optional<Student> findFirstByOrderByIdDesc();

	List<Student> findByName(String name);
}