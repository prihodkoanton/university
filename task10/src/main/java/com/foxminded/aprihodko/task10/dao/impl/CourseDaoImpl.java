package com.foxminded.aprihodko.task10.dao.impl;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.CourseDao;
import com.foxminded.aprihodko.task10.dao.mapper.CourseMapper;
import com.foxminded.aprihodko.task10.models.Course;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseDaoImpl extends AbstractCrudDao<Course, Long> implements CourseDao {

    public static final String FIND_BY_ID = "SELECT * FROM university.courses where course_id = ?";
    public static final String FIND_ALL = "SELECT * FROM university.courses";
    public static final String DELETE_BY_ID = "DELETE FROM university.courses where course_id = ?";
    public static final String FIND_BY_NAME = "SELECT * FROM university.courses where course_name = ?";
    public static final String CREATE = "INSERT INTO university.courses(course_id, course_name, course_description) VALUES (?, ?, ?)";
    public static final String UPDATE = "UPDATE university.courses SET course_name = ?, course_description = ? WHERE course_id = ?";

    private final JdbcTemplate jdbcTemplate;

    private final CourseMapper mapper;

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        mapper = new CourseMapper();
    }

    @Override
    public Optional<Course> findById(Long id) throws SQLException {
        return jdbcTemplate.query(FIND_BY_ID, mapper, id).stream().findFirst();
    }

    @Override
    public List<Course> findAll() throws SQLException {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        if (jdbcTemplate.update(DELETE_BY_ID, id) != 1) {
            throw new SQLException("Unable to delete course (id = " + id + ")");
        }
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public Optional<Course> findByName(String name) throws SQLException {
        return jdbcTemplate.query(FIND_BY_NAME, mapper, name).stream().findFirst();
    }

    @Override
    public Course create(Course entity) throws SQLException {
        if (jdbcTemplate.update(CREATE, entity.getId(), entity.getName(), entity.getDiscription()) != 1) {
            throw new SQLException("Unable to retrieve id" + entity.getId());
        }
        jdbcTemplate.update(CREATE, entity.getId(), entity.getName(), entity.getDiscription());
        return entity;
    }

    @Override
    public Course update(Course entity, Long id) throws SQLException {
        if (jdbcTemplate.update(CREATE, entity.getId(), entity.getName(), entity.getDiscription()) != 1) {
            throw new SQLException("Unable to update course " + entity);
        }
        jdbcTemplate.update(UPDATE, entity.getName(), entity.getDiscription(), id);
        return entity;
    }
}
