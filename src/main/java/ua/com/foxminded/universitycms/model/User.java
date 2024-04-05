package ua.com.foxminded.universitycms.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User extends Entity<Long> {

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;

	private UserRole role;

	public User(Long key, String name, String surname, String login, String password, UserRole role) {
		super(key);
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public User(Long key, String name, String surname, String login, String password) {
		super(key);
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
	}

	public User(String name, String surname, String login, String password) {
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
	}

	public User() {

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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String toString() {
		return "Name: " + name + ",  Surname: " + surname + ",  Login: " + login + ",  Password: " + password;
	}
}
