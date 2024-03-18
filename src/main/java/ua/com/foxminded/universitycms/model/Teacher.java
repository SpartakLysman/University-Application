package ua.com.foxminded.universitycms.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "teacher")
public class Teacher extends User implements Serializable {

	private static final long serialVersionUID = 3335155961633998707L;

	@OneToMany
	@JoinColumn(name = "teacher_id")
	private List<Course> courses;

	public Teacher(Long id, String name, String surname, String login, String password) {
		super(id, name, surname, login, password);
	}

	public Teacher() {

	}

	public List<Course> getCourses() {
		return courses;
	}
}
