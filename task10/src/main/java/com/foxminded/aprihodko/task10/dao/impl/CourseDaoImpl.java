package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.CourseDao;
import com.foxminded.aprihodko.task10.dao.mapper.CourseMapper;
import com.foxminded.aprihodko.task10.models.Course;

public class CurseDaoImpl extends AbstractCrudDao<Course, Long> implements CourseDao {

    public static final String FIND_BY_ID = "SELECT * FROM university.courses where course_id = ?";
    public static final String FIND_ALL = "SELECT * FROM university.courses";
    public static final String DELETE_BY_ID = "DELETE FROM university.courses where couse_id = ?";
    public static final String FIND_BY_NAME = "SELECT * FROM university.courses where course.name = ?";
    public static final String CREATE = "INSERT INTO university.courses(course_id, course_name, course_description) VALUES (?, ?, ?)";
    public static final String UPDATE = "UPDATE university.courses SET course_name = ?, course_description = ? WHERE course_id = ?";

    private JdbcTemplate jdbcTemplate;
    private CourseMapper mapper;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new CourseMapper();
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
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public Optional<Course> findByName(String name) throws SQLException {
        return jdbcTemplate.query(FIND_BY_NAME, mapper, name).stream().findFirst();
    }

    @Override
    protected Course create(Course entity) throws SQLException {
        jdbcTemplate.update(CREATE, entity.getId(), entity.getName(), entity.getDiscription());
        return entity;
    }

    @Override
    protected Course update(Course entity, Long id) throws SQLException {
        jdbcTemplate.update(UPDATE, entity.getName(), entity.getDiscription(), id);
        return entity;
    }
}
