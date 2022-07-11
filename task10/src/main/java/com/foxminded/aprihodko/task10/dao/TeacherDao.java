package com.foxminded.aprihodko.task10.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Teacher;

public interface TeacherDao extends CrudDao<Teacher, Long> {
    Optional<Teacher> findByName(Connection connection, String name) throws SQLException;

    List<Teacher> findByCourseId(Connection connection, Long id) throws SQLException;
}
