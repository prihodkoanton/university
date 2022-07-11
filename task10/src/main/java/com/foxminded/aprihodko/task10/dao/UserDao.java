package com.foxminded.aprihodko.task10.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.User;

public interface UserDao extends CrudDao<User, Long> {

    Optional<User> findByName(Connection connection, String name);
    List<User> findByUserType (Connection connection, String userType);
}
