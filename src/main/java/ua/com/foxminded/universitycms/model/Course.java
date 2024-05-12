package ua.com.foxminded.universitycms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "courses")
@jakarta.persistence.Entity
public class Course extends Entity<Long> implements Serializable {

	private static final long serialVersionUID = -7353139263354063173L;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<>();

	@ManyToMany(mappedBy = "courses")
	private List<Group> groups = new ArrayList<>();

	public Course(Long id, String title, String description) {
		super(id);
		this.title = title;
		this.description = description;
	}

	public Course(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Course() {

	}

	public void addStudent(Student student) {
		if (!this.students.contains(student)) {
			this.students.add(student);
			student.getCourses().add(this);
		}
	}

	public void deleteStudent(Student student) {
		if (this.students.contains(student)) {
			this.students.remove(student);
			student.getCourses().remove(this);
		}
	}

	public void addGroup(Group group) {
		this.groups.add(group);
	}

	public void deleteGroup(Group group1) {
		this.groups.remove(group1);
	}

	public List<Student> getStudents() {
		return students;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setDescription(String newDescription) {
		description = newDescription;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> newGroups) {
		groups = newGroups;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Course info: " + " \nTitle: " + title + ",  Description: " + description + ", " + "\n"
				+ "Number Of Students: " + students.size();
	}
}