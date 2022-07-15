package com.foxminded.aprihodko.task10.dao;

import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.User;

public interface UserDao extends CrudDao<User, Long> {

    Optional<User> findByName(String name);

    List<User> findByUserType(String userType);
}
