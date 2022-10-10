package com.foxminded.aprihodko.task10.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "university.users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends LongEntity implements Serializable {

    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_TYPE = "user_type";
    public static final String USER_ROLE = "user_role";
    public static final String USER_PASSWORD = "user_password";

    @Column(name = "user_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "user_password")
    private String passwordHash;

    public User(Long id, String name, UserType type, Role role, String passwordHash) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.role = role;
        this.passwordHash = passwordHash;
    }

    public User(Long id, String name, UserType type, Role role) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.role = role;
    }

    public User(Long id, String name, Role role, String passwordHash) {
        this(id, name, UserType.NONE, role, passwordHash);
    }

    public User(String name, UserType type, Role role, String passwordHash) {
        this(null, name, type, role, passwordHash);
    }

    public User(String name, Role role, String passwordHash) {
        this(null, name, UserType.NONE, role, passwordHash);
    }

    public User() {

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
