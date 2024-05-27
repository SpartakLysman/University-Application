package ua.com.foxminded.universitycms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.foxminded.universitycms.model.ScheduleEntry;

public interface ScheduleRepository extends JpaRepository<ScheduleEntry, Long> {

	List<ScheduleEntry> findByGroupId(Long groupId);

	List<ScheduleEntry> findByTeacherId(Long teacherId);
}
