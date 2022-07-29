package com.foxminded.aprihodko.task10.dao;

import com.foxminded.aprihodko.task10.models.Course;

import java.sql.SQLException;
import java.util.Optional;

public interface CourseDao extends CrudDao<Course, Long> {

    Optional<Course> findByName(String name) throws SQLException;
}
