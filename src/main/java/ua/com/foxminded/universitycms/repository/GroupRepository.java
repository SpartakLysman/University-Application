package ua.com.foxminded.universitycms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.universitycms.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	@Modifying
	@Query("DELETE FROM Group g WHERE g = :group")
	boolean deleteGroup(@Param("group") Group group);

	@Query("SELECT DISTINCT g FROM Group g LEFT JOIN FETCH g.courses WHERE g.id = :id")
	Optional<Group> findByIdWithCourses(Long id);// @Param("id")

	List<Group> findByTitle(String title);
}
