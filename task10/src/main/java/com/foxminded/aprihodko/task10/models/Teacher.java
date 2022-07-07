package com.foxminded.aprihodko.task10.models;

import java.util.Objects;

public class Teacher extends User {
    private Long courseId;

    public Teacher(Long id, String name, UserType type, Long courseId) {
        super(id, name, type);
        this.courseId = courseId;
    }

    public Long getGroupId() {
        return courseId;
    }

    public void setGroupId(Long groupId) {
        this.courseId = groupId;
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
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teacher other = (Teacher) obj;
        return Objects.equals(courseId, other.courseId);
    }
}
