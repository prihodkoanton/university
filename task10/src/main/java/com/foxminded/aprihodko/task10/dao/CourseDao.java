package com.foxminded.aprihodko.task10.dao;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.aprihodko.task10.models.Course;

public interface CourseDao extends CrudDao<Course, Long> {

    Optional<Course> findByName(String name) throws SQLException;
}
