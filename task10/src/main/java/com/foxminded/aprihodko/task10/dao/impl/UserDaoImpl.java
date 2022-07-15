package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.dao.mapper.UserMapper;
import com.foxminded.aprihodko.task10.models.User;

public class UserDaoImpl extends AbstractCrudDao<User, Long> implements UserDao {

    public static final String FIND_BY_ID = "SELECT * FROM university.users WHERE user_id = ?";
    public static final String FIND_ALL = "SELECT * FROM university.users";
    public static final String DELETE_BY_ID = "DELETE FROM university.users WHERE user_id = ?";
    public static final String FIND_BY_NAME = "SELECT * FROM university.users WHERE user_name = ?";
    public static final String FIND_BY_USER_TYPE = "SELECT * FROM university.users WHERE user_type = ?";
    public static final String CREATE = "INSERT INTO university.users (user_id,  user_name, user_type) VALUES (?, ?, ?)";
    public static final String UPDATE = "UPDATE university.users SET user_name = ?, user_type = ? WHERE user_id = ?)";

    private JdbcTemplate jdbcTemplate;
    private UserMapper mapper;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new UserMapper();
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        return jdbcTemplate.query(FIND_BY_ID, mapper, id).stream().findFirst();
    }

    @Override
    public List<User> findAll() throws SQLException {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return jdbcTemplate.query(FIND_BY_NAME, mapper, name).stream().findFirst();
    }

    @Override
    public List<User> findByUserType(String userType) {
        return jdbcTemplate.query(FIND_BY_USER_TYPE, mapper, userType);
    }

    @Override
    protected int create(User entity) throws SQLException {
        return jdbcTemplate.update(CREATE, entity.getId(), entity.getName(), entity.getType());
    }

    @Override
    protected int update(User entity, Long id) throws SQLException {
        return jdbcTemplate.update(UPDATE, entity.getName(), entity.getType(), id);
    }
}
