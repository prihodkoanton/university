package com.foxminded.aprihodko.task10.dao;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.task10.models.Course;

@Repository
public interface CourseDao extends JpaRepository<Course, Long> {

	Optional<Course> findByName(String name) throws SQLException;

	Optional<Course> findByDescription(String description) throws SQLException;
}
