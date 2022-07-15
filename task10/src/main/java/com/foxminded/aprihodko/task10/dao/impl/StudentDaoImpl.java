package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.StudentDao;
import com.foxminded.aprihodko.task10.dao.mapper.StudentMapper;
import com.foxminded.aprihodko.task10.models.Student;

public class StudentDaoImpl extends AbstractCrudDao<Student, Long> implements StudentDao {

    public static final String FIND_BY_ID = "SELECT * FROM university.students WHERE users_ref = ?";
    public static final String FIND_ALL = "SELECT * FROM university.students";
    public static final String DELETE_BY_ID = "DELETE FROM university.students WHERE users_ref = ?";
    public static final String FIND_BY_NAME = "SELECT * FROM university.students WHERE student_title = ?";
    public static final String FIND_BY_GROUP_ID = "SELECT * FROM university.students WHERE student_title = ?";
    public static final String CREATE = "INSERT INTO university.students (student_id, student_title) VALUES (?, ?)";
    public static final String UPDATE = "UPDATE university.students SET student_title) WHERE student_id = ?)";

    private JdbcTemplate jdbcTemplate;
    private StudentMapper mapper;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new StudentMapper();
    }

    @Override
    public Optional<Student> findById(Long id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Student> findAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<Student> findByName(String name) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Student> findByGroupId(String name) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected int create(Student entity) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected int update(Student entity, Long id) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }
}
