package com.foxminded.aprihodko.task10.models;

import java.util.Objects;

public class Group {
    private String name;
    private int id;
    private int sutentId;

    public Group(String name, int id, int sutentId) {
        this.name = name;
        this.id = id;
        this.sutentId = sutentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSutentId() {
        return sutentId;
    }

    public void setSutentId(int sutentId) {
        this.sutentId = sutentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sutentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        return id == other.id && Objects.equals(name, other.name) && sutentId == other.sutentId;
    }
}
