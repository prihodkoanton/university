package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.CourseDao;
import com.foxminded.aprihodko.task10.models.Course;

@Repository
public class CourseDaoImpl extends AbstractCrudDao<Course, Long> implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);

    @Override
    public Optional<Course> findById(Long id) throws SQLException {
        Course course = entityManager.find(Course.class, id);
        return course != null ? Optional.of(course) : Optional.empty();
    }

    @Override
    public List<Course> findAll() throws SQLException {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws SQLException {
        try {
            Course course = findById(id).orElseThrow();
            entityManager.remove(course);
        } catch (Exception e) {
            logger.error("Unable to user course (id = " + id + ")");
            throw new SQLException("Unable to delete course (id = " + id + ")");
        }
    }

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

    @Override
    @Transactional
    public Course create(Course entity) throws SQLException {
        entityManager.persist(entity);
        return new Course(entity.getId(), entity.getName(), entity.getDescription());
    }

    @Override
    @Transactional
    public Course update(Course entity) throws SQLException {
        Course course = findById(entity.getId()).orElseThrow();
        entityManager.merge(course);
        return new Course(entity.getId(), entity.getName(), entity.getDescription());
    }
}
