package com.foxminded.aprihodko.task10.models;

import java.util.Objects;

public class Student extends User {

    private Long groupdId;

    public Student(Long id, String name, UserType type, Long groupdId) {
        super(id, name, type);
        this.groupdId = groupdId;
    }

    public Long getGroupdId() {
        return groupdId;
    }

    public void setGroupdId(Long groupdId) {
        this.groupdId = groupdId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(groupdId);
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
        Student other = (Student) obj;
        return Objects.equals(groupdId, other.groupdId);
    } 
}
