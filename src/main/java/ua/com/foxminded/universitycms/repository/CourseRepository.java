package ua.com.foxminded.universitycms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.universitycms.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	@Modifying
	@Query("DELETE FROM Course c WHERE c = :course")
	boolean deleteCourse(@Param("course") Course course);

	void deleteById(@Param("id") Long id);

	List<Course> findByTitle(String title);
}
