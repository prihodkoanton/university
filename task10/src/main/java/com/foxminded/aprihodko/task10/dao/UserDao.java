package com.foxminded.aprihodko.task10.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.User;

public interface UserDao extends CrudDao<User, Long> {

    Optional<User> findByName(String name);

    List<User> findByUserType(String userType);

    List<User> findTeacherByCourseId(User entity, Long id) throws SQLException;

    List<User> findStudentByGroupId(User entity,Long id) throws SQLException;
}
