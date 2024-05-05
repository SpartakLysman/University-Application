package ua.com.foxminded.universitycms.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Entity<K extends Comparable<K>> {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private K id;

	public Entity(K id) {
		this.id = id;
	}

	public Entity() {

	}

	public K getId() {
		return id;
	}

	public void setId(K newId) {
		this.id = newId;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (this.getClass() != object.getClass()) {
			return false;
		}

		Entity<?> entity = (Entity<?>) object;
		return entity.id.equals(id);
	}

	public int hashCode() {
		int number = 5;
		number = number * 5 + id.hashCode();
		return number;
	}

	public String toString() {
		return "Id: " + this.id;
	}
}
