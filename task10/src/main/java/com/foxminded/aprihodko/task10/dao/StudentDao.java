package com.foxminded.aprihodko.task10.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Student;

public interface StudentDao extends CrudDao<Student, Long> {
    Optional<Student> findByName(Connection connection, String name) throws SQLException;

    List<Student> findByGroupId(Connection connection, String name) throws SQLException;
}
