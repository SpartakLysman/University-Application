package ua.com.foxminded.universitycms.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleEntry {

	private LocalDate date;
	private LocalTime time;
	private Course course;
	private String classroom;

	public ScheduleEntry() {

	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	public Course getCourse() {
		return course;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setDate(LocalDate newDate) {
		date = newDate;
	}

	public void setTime(LocalTime newTime) {
		time = newTime;
	}

	public void setCourse(Course newCourse) {
		course = newCourse;
	}

	public void setClassroom(String newClassroom) {
		classroom = newClassroom;
	}
}
