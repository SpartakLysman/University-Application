package ua.com.foxminded.universitycms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "students")
public class Student extends User implements Serializable {

	@Column(name = "groups_group_id")
	private Group group_id;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinTable(name = "students_courses", schema = "application", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<>();

	private static final long serialVersionUID = -7353839263354063175L;

	public Student(long key, Group newGroup_id, String name, String surname, String login, String password) {
		super(key, name, surname, login, password);
		this.group_id = newGroup_id;
		this.courses = new ArrayList<>();
	}

	public Student(Group newGroup_id, String name, String surname, String login, String password,
			List<Course> courses) {
		super(name, surname, login, password);
		this.group_id = newGroup_id;
		this.courses = new ArrayList<>();
	}

	public Student(Group newGroup_id, String name, String surname, String login, String passsword) {
		super(name, surname, login, passsword);
		this.group_id = newGroup_id;
		this.courses = new ArrayList<>();
	}

	public Student(String name, String surname, String login, String password) {
		super(name, surname, login, password);
	}

	public Student() {

	}

	public void addCourse(Course course) {
		if (courses.size() < 4) {
			this.courses.add(course);
			course.addStudent(this);
		} else {

			System.out.println("You already added max amount of courses");
		}
	}

	public void deleteCourse(Course course) {
		if (courses.contains(course)) {
			this.courses.remove(course);
			course.deleteStudent(this);
		} else {

			System.out.println("The course not faund");
		}
	}

	public List<Course> getCourses() {
		return courses;
	}

	public int getMaxCourses() {
		return 4;
	}

	public Group getGroupId() {
		return group_id;
	}

	public void setGroupId(Group newGroup_id) {
		this.group_id = newGroup_id;
	}

	public String toString() {
		return "Group id: " + group_id;
	}
}