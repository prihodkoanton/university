package com.foxminded.aprihodko.task10.models;

import java.util.Objects;

public class Student extends User {

    public static final String GROUP_REF = "group_ref";
    private Long groupId;

    public Student(Long id, String name, Long groupId) {
        super(id, name, UserType.STUDENT);
        this.groupId = groupId;
    }

    public Student(Long id, String name) {
        super(id, name, UserType.STUDENT);
    }

    public Student(String name, Long groupId) {
        super(null, name, UserType.STUDENT);
        this.groupId = groupId;
    }

    public Student() {

    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupdId) {
        this.groupId = groupdId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(groupId);
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
        Student other = (Student) obj;
        return Objects.equals(groupId, other.groupId);
    }

    @Override
    public String toString() {
        return "Student [groupdId=" + groupId + ", id=" + id + "]";
    }
}
