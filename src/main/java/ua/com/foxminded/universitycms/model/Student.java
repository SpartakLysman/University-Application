package ua.com.foxminded.universitycms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "students")
@jakarta.persistence.Entity
public class Student extends User implements Serializable {

	// @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	// @JoinTable(name = "groups", schema = "public", joinColumns = @JoinColumn(name
	// = "group_id"), inverseJoinColumns = @JoinColumn(name = "id"))
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "group_id")
	private Group group;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinTable(name = "students_courses", schema = "public", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<>();

	private static final long serialVersionUID = -7353839263354063175L;

	public Student(Long id, Group newGroup, String name, String surname, String login, String password) {
		super(id, name, surname, login, password);
		this.group = newGroup;
		this.courses = new ArrayList<>();
	}

	public Student(Group newGroup, String name, String surname, String login, String password, List<Course> courses) {
		super(name, surname, login, password);
		this.group = newGroup;
		this.courses = new ArrayList<>();
	}

	public Student(Group newGroup, String name, String surname, String login, String passsword) {
		super(name, surname, login, passsword);
		this.group = newGroup;
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group newGroup) {
		this.group = newGroup;
	}

	public String toString() {
		return "Group id: " + group.getId();
	}
}