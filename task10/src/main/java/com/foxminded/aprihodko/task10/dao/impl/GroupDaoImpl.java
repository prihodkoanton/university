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
import com.foxminded.aprihodko.task10.dao.GroupDao;
import com.foxminded.aprihodko.task10.dao.mapper.GroupMapper;
import com.foxminded.aprihodko.task10.models.Group;

@Repository
public class GroupDaoImpl extends AbstractCrudDao<Group, Long> implements GroupDao {

    private static final Logger logger = LoggerFactory.getLogger(GroupDaoImpl.class);

    public static final String FIND_BY_ID = "SELECT * FROM university.groups WHERE group_id = ?";
    public static final String FIND_ALL = "SELECT * FROM university.groups";
    public static final String DELETE_BY_ID = "DELETE FROM university.groups WHERE group_id = ?";
    public static final String FIND_BY_NAME = "SELECT * FROM university.groups WHERE group_name = ?";
    public static final String CREATE = "INSERT INTO university.groups (group_name) VALUES (?)";
    public static final String UPDATE = "UPDATE university.groups SET group_name = ? WHERE group_id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final GroupMapper mapper;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public GroupDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        mapper = new GroupMapper();
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("university.groups")
                .usingColumns("group_name").usingGeneratedKeyColumns("group_id");
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
        int deleteRowCount = jdbcTemplate.update(DELETE_BY_ID, id);
        if (deleteRowCount != 1) {
            logger.error("Unable to delete group (id = " + id + ")");
            throw new SQLException("Unable to delete course (id = " + id + ")");
        }
    }

    @Override
    public Optional<Group> findByName(String name) throws SQLException {
        return jdbcTemplate.query(FIND_BY_NAME, mapper, name).stream().findFirst();
    }

    @Override
    public Group create(Group entity) throws SQLException {
        Map<String, Object> groupParameters = new HashMap<String, Object>();
        groupParameters.put("group_name", entity.getName());
        Number id = simpleJdbcInsert.executeAndReturnKey(groupParameters);
        if (id == null) {
            logger.error("Unable to create Group:{}", entity);
            throw new SQLException("Unable to retrieve id" + entity.getId());
        }
        return new Group(id.longValue(), entity.getName());
    }

    @Override
    public Group update(Group entity) throws SQLException {
        int updatedRowCount = jdbcTemplate.update(UPDATE, entity.getName(), entity.getId());
        if (updatedRowCount != 1) {
            logger.error("Unable to update Group:{}", entity);
            throw new SQLException("Unable to update group " + entity);
        }
        return new Group(entity.getId(), entity.getName());
    }
}
