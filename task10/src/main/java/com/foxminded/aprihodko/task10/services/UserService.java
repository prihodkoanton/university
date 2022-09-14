package com.foxminded.aprihodko.task10.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;

public interface UserService {

    Optional<User> findById(Long id) throws SQLException;

    List<User> findAll() throws SQLException;

    void deleteById(Long id) throws SQLException;

    Optional<User> findByName(String name);

    List<User> findByType(UserType userType);

    User save(User entity) throws SQLException;

    List<Student> findStudentByGroupId(Long id) throws SQLException;

    List<Teacher> findTeacherByCourseId(Long id) throws SQLException;
}
