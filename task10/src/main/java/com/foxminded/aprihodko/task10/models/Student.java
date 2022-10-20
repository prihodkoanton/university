package com.foxminded.aprihodko.task10.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends User implements Serializable {

    public static final String GROUP_REF = "group_ref";

    @Column(name = "group_id", nullable = false, insertable = false, updatable = false)
    private Long groupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "ID")
    private Group group;

    public Student(Long id, String name, Long groupId) {
        super(id, name, UserType.STUDENT, Role.USER);
        this.groupId = groupId;
    }

    public Student(Long id, String name, Long groupId, String passwordHash) {
        super(id, name, UserType.STUDENT, Role.USER, passwordHash);
        this.groupId = groupId;
    }

    public Student(String name, Long groupId, String passwordHash) {
        super(null, name, UserType.STUDENT, Role.USER, passwordHash);
        this.groupId = groupId;
    }

    public Student(String name, Long groupId) {
        super(null, name, UserType.STUDENT, Role.USER);
        this.groupId = groupId;
    }

    public static String getGroupRef() {
        return GROUP_REF;
    }

    public Student() {

    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
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
