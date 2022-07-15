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

    private JdbcTemplate jdbcTemplate;
    private UserMapper mapper;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new UserMapper();
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<User> findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findByUserType(String userType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected int create(User entity) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected int update(User entity, Long id) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }
}
