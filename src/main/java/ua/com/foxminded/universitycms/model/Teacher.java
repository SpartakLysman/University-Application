package ua.com.foxminded.universitycms.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "teachers")
@jakarta.persistence.Entity
public class Teacher extends Entity<Long> implements Serializable {

	private static final long serialVersionUID = 3335155961633998707L;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;

	@OneToMany
	@JoinColumn(name = "teacher_id")
	private List<Course> courses;

	public Teacher(Long id, String name, String surname, String login, String password) {
		super(id);
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
	}

	public Teacher(String name, String surname, String login, String password) {
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
	}

	public Teacher() {

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

	public String toString() {
		return "Name: " + name + ",  Surname: " + surname + ",  Login: " + login + ",  Password: " + password;
	}
}
