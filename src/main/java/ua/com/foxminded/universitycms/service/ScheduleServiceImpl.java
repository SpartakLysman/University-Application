package ua.com.foxminded.universitycms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.com.foxminded.universitycms.model.ScheduleEntry;
import ua.com.foxminded.universitycms.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	private final ScheduleRepository scheduleRepository;

	public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}

	@Override
	public List<ScheduleEntry> getStudentSchedule(Long studentId) {
		return scheduleRepository.findByStudentId(studentId);
	}

	@Override
	public List<ScheduleEntry> getTeacherSchedule(Long teacherId) {
		return scheduleRepository.findByTeacherId(teacherId);
	}

	@Override
	public List<ScheduleEntry> findAll() {
		return scheduleRepository.findAll();
	}

	@Override
	public ScheduleEntry findById(Long id) {
		return scheduleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid schedule Id:" + id));
	}

	@Override
	public void save(ScheduleEntry scheduleEntry) {
		scheduleRepository.save(scheduleEntry);
	}
}
