package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.task10.dao.CourseDao;
import com.foxminded.aprihodko.task10.models.Course;

@Repository
public abstract class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);

    @Override
    public Optional<Course> findByName(String name) throws SQLException {
        TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE course_name = '" + name + "'",
                Course.class);
        Course course = query.getSingleResult();
        return course != null ? Optional.of(course) : Optional.empty();
    }

    @Override
    public Optional<Course> findByDescription(String description) throws SQLException {
        TypedQuery<Course> query = entityManager
                .createQuery("FROM Course WHERE course_description = '" + description + "'", Course.class);
        Course course = query.getSingleResult();
        return course != null ? Optional.of(course) : Optional.empty();
    }
}
