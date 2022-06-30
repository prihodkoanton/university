package com.foxminded.aprihodko.task10.models;

import java.util.Objects;

public class Teacher extends User {
    private String firstName;
    private String lastname;
    private int id;

    public Teacher(String firstName, String lastname, int id) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, id, lastname);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teacher other = (Teacher) obj;
        return Objects.equals(firstName, other.firstName) && id == other.id && Objects.equals(lastname, other.lastname);
    }
}
