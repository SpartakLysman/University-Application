package ua.com.foxminded.universitycms.service;

import java.util.List;

import ua.com.foxminded.universitycms.model.ScheduleEntry;

public interface ScheduleService {

	List<ScheduleEntry> getStudentSchedule(Long studentId);

	List<ScheduleEntry> getTeacherSchedule(Long teacherId);
}
