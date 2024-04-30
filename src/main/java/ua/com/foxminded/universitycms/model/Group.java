package ua.com.foxminded.universitycms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Table(name = "groups")
@jakarta.persistence.Entity
public class Group extends Entity<Long> implements Serializable {

	private static final long serialVersionUID = -7353719263354063173L;

	@Column(name = "title")
	private String title;

	@ManyToMany
	@JoinTable(name = "groups_courses", schema = "public", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<>();

	public Group(Long id, String title) {
		super(id);
		this.title = title;
	}

	public Group() {

	}

	public void addCourse(Course course) {
		if (!courses.contains(course)) {
			this.courses.add(course);
		} else {

			System.out.println("You already added have this course in group");
		}
	}

	public void deleteCourse(Course course) {
		if (courses.contains(course)) {
			courses.remove(course);
		} else {

			System.out.println("The course not found");
		}
	}

	public List<Course> getCourses() {
		return courses;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>();
		for (Course course : courses) {
			students.addAll(course.getStudents());
		}
		return students;
	}

	public void removeStudent(Student student) {
		for (Course course : courses) {
			if (course.getStudents().contains(student)) {
				course.deleteStudent(student);
				return;
			}
		}
		System.out.println("The student not found in any course of this group");
	}

	public void addStudent(Student student) {
		for (Course course : courses) {
			if (!course.getStudents().contains(student)) {
				course.addStudent(student);
			}
		}
	}

	public String toString() {
		return "Title" + title;
	}
}
