package ua.com.foxminded.universitycms.model;

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
public class UserTable extends User {

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "group_id")
	private Group group;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	public UserTable(Long id, Group newGroup, String name, String surname, String login, String password,
			UserRole role) {
		super(id, name, surname, login, password);
		this.group = newGroup;
		this.role = role;
	}

	public UserTable(Long id, String name, String surname, String login, String password, UserRole role) {
		super(id, name, surname, login, password);
		this.role = role;
	}

	public UserTable(Group newGroup, String name, String surname, String login, String password, UserRole role) {
		super(name, surname, login, password);
		this.group = newGroup;
		this.role = role;
	}

	public UserTable(Long id, Group newGroup, String name, String surname, String login, String password) {
		super(id, name, surname, login, password);
		this.group = newGroup;
	}

	public UserTable() {

	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group newGroup) {
		this.group = newGroup;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String toString() {
		return "Group id: " + group.getId() + ", Role: " + role;
	}
}
