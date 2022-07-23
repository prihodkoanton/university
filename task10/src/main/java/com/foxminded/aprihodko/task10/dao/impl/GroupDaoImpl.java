package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.GroupDao;
import com.foxminded.aprihodko.task10.dao.mapper.GroupMapper;
import com.foxminded.aprihodko.task10.models.Group;

public class GroupDaoImpl extends AbstractCrudDao<Group, Long> implements GroupDao {

    public static final String FIND_BY_ID = "SELECT * FROM university.groups WHERE group_id = ?";
    public static final String FIND_ALL = "SELECT * FROM university.groups";
    public static final String DELETE_BY_ID = "DELETE FROM university.groups WHERE group_id = ?";
    public static final String FIND_BY_NAME = "SELECT * FROM university.groups WHERE group_name = ?";
    public static final String CREATE = "INSERT INTO university.groups (group_id, group_name) VALUES (?, ?)";
    public static final String UPDATE = "UPDATE university.groups SET group_name = ? WHERE group_id = ?";

    private JdbcTemplate jdbcTemplate;
    private GroupMapper mpper;

    public GroupDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        mapper = new GroupMapper();
    }

    @Override
    public Optional<Group> findById(Long id) throws SQLException {
        return jdbcTemplate.query(FIND_BY_ID, mapper, id).stream().findFirst();
    }

    @Override
    public List<Group> findAll() throws SQLException {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public Optional<Group> findByName(String name) throws SQLException {
        return jdbcTemplate.query(FIND_BY_NAME, mapper, name).stream().findFirst();
    }

    @Override
    protected Group create(Group entity) throws SQLException {
        jdbcTemplate.update(CREATE, entity.getId(), entity.getName());
        return entity;
    }

    @Override
    protected Group update(Group entity, Long id) throws SQLException {
        jdbcTemplate.update(UPDATE, entity.getName(), id);
        return entity;
    }
}
