package ua.com.foxminded.universitycms.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.com.foxminded.universitycms.model.Course;
import ua.com.foxminded.universitycms.model.ScheduleEntry;
import ua.com.foxminded.universitycms.repository.CourseRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	private final CourseRepository courseRepository;

	public ScheduleServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public List<ScheduleEntry> getStudentSchedule(Long studentId) {
		List<Course> enrolledCourses = courseRepository.findByStudentId(studentId);
		List<ScheduleEntry> schedule = new ArrayList<>();

		LocalDate staticDate = LocalDate.of(2024, Month.MAY, 31);
		LocalTime staticTime = LocalTime.of(9, 0);

		for (Course course : enrolledCourses) {
			ScheduleEntry entry = new ScheduleEntry();
			entry.setDate(staticDate);
			entry.setTime(staticTime);
			entry.setCourse(course);

			schedule.add(entry);
		}
		return schedule;
	}

	@Override
	public List<ScheduleEntry> getTeacherSchedule(Long teacherId) {
		List<Course> teacherCourses = courseRepository.findByTeacherId(teacherId);
		List<ScheduleEntry> schedule = new ArrayList<>();

		LocalDate staticDate = LocalDate.of(2024, Month.MAY, 31);
		LocalTime staticTime = LocalTime.of(9, 0);

		for (Course course : teacherCourses) {
			ScheduleEntry entry = new ScheduleEntry();
			entry.setDate(staticDate);
			entry.setTime(staticTime);
			entry.setCourse(course);

			schedule.add(entry);
		}
		return schedule;
	}
}
