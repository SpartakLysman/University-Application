package ua.com.foxminded.universitycms.model;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "schedule")
@jakarta.persistence.Entity
public class ScheduleEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@ManyToOne
	@JoinColumn(name = "teacher_id", nullable = false)
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	private LocalDateTime dateTime;

	private String classroom;

	public ScheduleEntry() {

	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public Course getCourse() {
		return course;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDateTime(LocalDateTime newDateTime) {
		dateTime = newDateTime;
	}

	public void setCourse(Course newCourse) {
		course = newCourse;
	}

	public void setClassroom(String newClassroom) {
		classroom = newClassroom;
	}
}
