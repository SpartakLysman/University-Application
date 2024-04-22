package ua.com.foxminded.universitycms.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "users")
@jakarta.persistence.Entity
public class User extends Entity<Long> implements Serializable {

	private static final long serialVersionUID = 3435155961633998707L;

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

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	public User(Long id, String name, String surname, String login, String password, UserRole role) {
		super(id);
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public User(Long id, String name, String surname, String login, String password) {
		super(id);
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

	public UserRole getRole() {
		return role;
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

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String toString() {
		return "Group id: " + group.getId() + ", Name: " + name + ",  Surname: " + surname + ",  Login: " + login
				+ ",  Password: " + password + ", Role: " + role;
	}
}
