package ua.com.foxminded.universitycms.model;

import java.io.Serializable;

import jakarta.persistence.Table;

@Table(name = "admin")
@jakarta.persistence.Entity
public class Admin extends User implements Serializable {

	private static final long serialVersionUID = 4279373010267934224L;

	public Admin(Long id, String name, String surname, String login, String password) {
		super(id, name, surname, login, password);
	}

	public Admin() {

	}
}
