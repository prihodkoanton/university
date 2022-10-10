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

//    private final JdbcTemplate jdbcTemplate;
//    private final CourseMapper mapper;
//    private final SimpleJdbcInsert simpleJdbcInsert;
    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);

//    public CourseDaoImpl(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

//    public static final String FIND_BY_ID = "SELECT * FROM university.courses where course_id = ?";
//    public static final String FIND_ALL = "SELECT * FROM university.courses";
//    public static final String DELETE_BY_ID = "DELETE FROM university.courses where course_id = ?";
//    public static final String FIND_BY_NAME = "SELECT * FROM university.courses where course_name = ?";
//    public static final String FIND_BY_DESCRIPTION = "SELECT * FROM university.courses where course_description = ?";
//    public static final String CREATE = "INSERT INTO university.courses(course_name, course_description) VALUES (?, ?)";
//    public static final String UPDATE = "UPDATE university.courses SET course_name = ?, course_description = ? WHERE course_id = ?";

    @Override
    public Optional<Course> findById(Long id) throws SQLException {
        Course course = entityManager.find(Course.class, id);
//        Course course = (Course) entityManager.createQuery("FROM university.courses c where c.id = :id")
//                .setParameter("id", id).getSingleResult();
        return course != null ? Optional.of(course) : Optional.empty();
//        return Optional.of(course);
    }

    @Override
    public List<Course> findAll() throws SQLException {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws SQLException {
        Course course = findById(id).orElseThrow();
        entityManager.remove(course);
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
