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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "students")
@jakarta.persistence.Entity
public class Student extends Entity<Long> implements Serializable {

	private static final long serialVersionUID = -7353839263354063175L;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "group_id")
	private Group group;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinTable(name = "students_courses", schema = "public", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<>();

	public Student(Long id, Group newGroup, String name, String surname, String login, String password) {
		super(id);
		this.group = newGroup;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.courses = new ArrayList<>();
	}

	public Student(Group newGroup, String name, String surname, String login, String password, List<Course> courses) {
		this.group = newGroup;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.courses = new ArrayList<>();
	}

	public Student(Group newGroup, String name, String surname, String login, String password) {
		this.group = newGroup;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.courses = new ArrayList<>();
	}

	public Student(String name, String surname, String login, String password) {
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
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

			System.out.println("The course not found");
		}
	}

	public Group getGroup() {
		return group;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setGroup(Group newGroup) {
		this.group = newGroup;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setSurname(String newSurname) {
		this.surname = newSurname;
	}

	public void setLogin(String newLogin) {
		this.login = newLogin;
	}

	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public int getMaxCourses() {
		return 4;
	}

	public String toString() {
		return "Group id: " + group.getId() + ", Name: " + name + ",  Surname: " + surname + ",  Login: " + login
				+ ",  Password: " + password;
	}
}