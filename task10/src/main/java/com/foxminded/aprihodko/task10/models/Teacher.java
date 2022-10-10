package com.foxminded.aprihodko.task10.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "university.teachers")
public class Teacher extends User implements Serializable {

    public static final String COURSE_REF = "course_ref";

    @Column(name = "course_ref")
    private Long courseId;

    public Teacher(Long id, String name, Long courseId) {
        super(id, name, UserType.TEACHER, Role.USER);
        this.courseId = courseId;
    }

    public Teacher(Long id, String name, String passwordHash) {
        super(id, name, UserType.TEACHER, Role.USER, passwordHash);
    }

    public Teacher(Long id, String name, Long courseId, String passwordHash) {
        super(id, name, UserType.TEACHER, Role.USER, passwordHash);
        this.courseId = courseId;
    }

    public Teacher(String name, Long courseId, String passwordHash) {
        super(null, name, UserType.TEACHER, Role.USER, passwordHash);
        this.courseId = courseId;
    }

    public Teacher(String name, Long courseId) {
        super(null, name, UserType.TEACHER, Role.USER);
        this.courseId = courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Teacher() {

    }

    @Override
    public Long getId() {
        return id;
    }

    public static String getCourseRef() {
        return COURSE_REF;
    }

    public Long getCourseId() {
        return courseId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(courseId);
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
        Teacher other = (Teacher) obj;
        return Objects.equals(courseId, other.courseId);
    }

    @Override
    public String toString() {
        return "Teacher [courseId=" + courseId + ", id=" + id + "]";
    }

}
