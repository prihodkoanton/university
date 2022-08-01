package com.foxminded.aprihodko.task10.models;

import java.util.Objects;

public class User extends LongEntity {

    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_TYPE = "user_type";

    private String name;
    private UserType type;

    public User(Long id, String name, UserType type) {
        super(id);
        this.name = name;
        this.type = type;
    }

    public User(String name, UserType type) {
        this(null, name, type);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(name, type);
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
        User other = (User) obj;
        return Objects.equals(name, other.name) && type == other.type;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", type=" + type + ", id=" + id + "]";
    }
}
