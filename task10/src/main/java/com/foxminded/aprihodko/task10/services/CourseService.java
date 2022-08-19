package com.foxminded.aprihodko.task10.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Course;

public interface CourseService {

    Optional<Course> findById(Long id) throws SQLException;

    List<Course> findAll() throws SQLException;

    void deleteById(Long id) throws SQLException;

    Optional<Course> findByName(String name) throws SQLException;

    Optional<Course> findByDescription(String descripteon) throws SQLException;

    Course save(Course entity) throws SQLException;
}
