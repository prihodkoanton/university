package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.CourseDao;
import com.foxminded.aprihodko.task10.dao.mapper.CourseMapper;
import com.foxminded.aprihodko.task10.models.Course;

@Repository
public class CourseDaoImpl extends AbstractCrudDao<Course, Long> implements CourseDao {

    private static final Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);

    public static final String FIND_BY_ID = "SELECT * FROM university.courses where course_id = ?";
    public static final String FIND_ALL = "SELECT * FROM university.courses";
    public static final String DELETE_BY_ID = "DELETE FROM university.courses where course_id = ?";
    public static final String FIND_BY_NAME = "SELECT * FROM university.courses where course_name = ?";
    public static final String FIND_BY_DESCRIPTION = "SELECT * FROM university.courses where course_description = ?";
    public static final String CREATE = "INSERT INTO university.courses(course_name, course_description) VALUES (?, ?)";
    public static final String UPDATE = "UPDATE university.courses SET course_name = ?, course_description = ? WHERE course_id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final CourseMapper mapper;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        mapper = new CourseMapper();
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("university.courses")
                .usingColumns("course_name", "course_description").usingGeneratedKeyColumns("course_id");
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
        int deleteRowCount = jdbcTemplate.update(DELETE_BY_ID, id);
        if (deleteRowCount != 1) {
            throw new SQLException("Unable to delete course (id = " + id + ")");
        }
    }

    @Override
    public Optional<Course> findByName(String name) throws SQLException {
        return jdbcTemplate.query(FIND_BY_NAME, mapper, name).stream().findFirst();
    }

    @Override
    public Optional<Course> findByDescription(String description) throws SQLException {
        return jdbcTemplate.query(FIND_BY_DESCRIPTION, mapper, description).stream().findFirst();
    }

    @Override
    public Course create(Course entity) throws SQLException {
        Map<String, Object> courseParameters = new HashMap<String, Object>();
        courseParameters.put("course_name", entity.getName());
        courseParameters.put("course_description", entity.getDescription());
        Number id = simpleJdbcInsert.executeAndReturnKey(courseParameters);
        if (id == null) {
            logger.error("Unable to create Course: {}", entity);
            throw new SQLException("Unable to retrieve id" + entity.getId());
        }
        return new Course(entity.getName(), entity.getDescription());
    }

    @Override
    public Course update(Course entity) throws SQLException {
        int updatedRowCount = jdbcTemplate.update(UPDATE, entity.getName(), entity.getDescription(), entity.getId());
        if (updatedRowCount != 1) {
            logger.error("Unable to update Course: {}", entity);
            throw new SQLException("Unable to update course " + entity);
        }
        return new Course(entity.getId(), entity.getName(), entity.getDescription());
    }
}
