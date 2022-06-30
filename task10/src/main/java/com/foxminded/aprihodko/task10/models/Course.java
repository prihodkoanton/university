package com.foxminded.aprihodko.task10.models;

import java.util.Objects;

public class Course {
    private int id;
    private String name;
    private int teacherId;

    public Course(int id, String name, int teacherId) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teacherId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        return id == other.id && Objects.equals(name, other.name) && teacherId == other.teacherId;
    }
}
