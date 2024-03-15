package ua.com.foxminded.universitycms.model;

import java.io.Serializable;

import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "admin")
public class Admin extends User implements Serializable {

	private static final long serialVersionUID = 4279373010267934224L;

	public Admin() {

	}
}
