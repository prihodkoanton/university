package com.foxminded.aprihodko.task10.models;

import java.util.Objects;

public class Course {
    private Long id;
    private String name;
    private String discription;

    public Course(Long id, String name, String discription) {
        this.id = id;
        this.name = name;
        this.discription = discription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discription, id, name);
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
        return Objects.equals(discription, other.discription) && Objects.equals(id, other.id)
                && Objects.equals(name, other.name);
    }
}
