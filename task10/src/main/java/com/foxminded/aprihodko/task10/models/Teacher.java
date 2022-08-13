package com.foxminded.aprihodko.task10.models;

import java.util.Objects;

public class Teacher extends User {

    public static final String COURSE_REF = "course_ref";

    private Long courseId;

    public Teacher(Long id, String name, Long courseId) {
        super(id, name, UserType.TEACHER);
        this.courseId = courseId;
    }

    public Teacher(Long id, String name) {
        super(id, name, UserType.TEACHER);
    }

    public Teacher(String name, Long courseId) {
        super(null, name, UserType.TEACHER);
        this.courseId = courseId;
    }

    public Teacher() {

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
