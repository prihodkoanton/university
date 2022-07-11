package com.foxminded.aprihodko.task10.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Course;

public interface CourseDao extends CrudDao<Course, Long> {

    Optional<Course> findByName(Connection connection, String name) throws SQLException;
}
