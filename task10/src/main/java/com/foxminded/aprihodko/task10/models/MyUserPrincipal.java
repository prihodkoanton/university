package com.foxminded.aprihodko.task10.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserPrincipal implements UserDetails {

    private final String name;
    private final UserType type;
    private final Role role;
    private final String passwordHash;
    private final List<SimpleGrantedAuthority> authorities;

    public MyUserPrincipal(String name, UserType type, Role role, String passwordHash,
            List<SimpleGrantedAuthority> simpleGrantedAuthorities) {
        this.name = name;
        this.type = type;
        this.role = role;
        this.passwordHash = passwordHash;
        this.authorities = simpleGrantedAuthorities;
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    public Role getRole() {
        return role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static UserDetails fromUser(com.foxminded.aprihodko.task10.models.User user) {
        return new User(user.getName(), user.getPasswordHash(), user.getRole().getAuthorities());
    }
}