package com.foxminded.aprihodko.task10.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;

@Component
public interface UserDao extends CrudDao<User, Long> {

    Optional<User> findByName(String name);

    List<User> findByType(UserType userType);

    List<Teacher> findTeacherByCourseId(Long id) throws SQLException;

    List<Student> findStudentByGroupId(Long id) throws SQLException;
}
