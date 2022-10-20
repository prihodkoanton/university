package com.foxminded.aprihodko.task10.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rooms")
public class Room extends LongEntity implements Serializable {

	public static final String ROOM_ID = "room_id";
	public static final String ROOM_TITLE = "room_title";

	@Column(name = "room_title")
	@NotEmpty(message = "Room's title cannot be empty.")
	@Size(min = 2, max = 250)
	private String title;

	public Room(Long id, String title) {
		this.id = id;
		this.title = title;
	}

	public Room(String title) {
		this(null, title);
	}

	public Room() {

	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(title);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Room other = (Room) obj;
		return Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Room [title=" + title + ", id=" + id + "]";
	}

}
