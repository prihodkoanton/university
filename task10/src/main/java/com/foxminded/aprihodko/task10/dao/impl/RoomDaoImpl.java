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
import com.foxminded.aprihodko.task10.dao.RoomDao;
import com.foxminded.aprihodko.task10.dao.mapper.RoomMapper;
import com.foxminded.aprihodko.task10.models.Room;

@Repository
public class RoomDaoImpl extends AbstractCrudDao<Room, Long> implements RoomDao {

    private static final Logger logger = LoggerFactory.getLogger(RoomDaoImpl.class);

    public static final String FIND_BY_ID = "SELECT * FROM university.rooms WHERE room_id = ?";
    public static final String FIND_ALL = "SELECT * FROM university.rooms";
    public static final String DELETE_BY_ID = "DELETE FROM university.rooms WHERE room_id = ?";
    public static final String FIND_BY_TITLE = "SELECT * FROM university.rooms WHERE room_title = ?";
    public static final String CREATE = "INSERT INTO university.rooms (room_id, room_title) VALUES (?, ?)";
    public static final String UPDATE = "UPDATE university.rooms SET room_title = ? WHERE room_id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final RoomMapper mapper;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public RoomDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        mapper = new RoomMapper();
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("university.rooms")
                .usingColumns("room_title").usingGeneratedKeyColumns("room_id");
    }

    @Override
    public Optional<Room> findById(Long id) throws SQLException {
        return jdbcTemplate.query(FIND_BY_ID, mapper, id).stream().findFirst();
    }

    @Override
    public List<Room> findAll() throws SQLException {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        int deleteRowCount = jdbcTemplate.update(DELETE_BY_ID, id);
        if (deleteRowCount != 1) {
            logger.error("Unable to delete room (id = " + id + ")");
            throw new SQLException("Unable to delete course (id = " + id + ")");
        }
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public Optional<Room> findByTitle(String name) {
        return jdbcTemplate.query(FIND_BY_TITLE, mapper, name).stream().findFirst();
    }

    @Override
    public Room create(Room entity) throws SQLException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("room_title", entity.getTitle());
        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        if (id == null) {
            logger.error("Unable to create Room:{}", entity);
            throw new SQLException("Unable to retrieve id" + entity.getId());
        }
        return new Room(id.longValue(), entity.getTitle());
    }

    @Override
    public Room update(Room entity) throws SQLException {
        int updatedRowCount = jdbcTemplate.update(CREATE, entity.getId(), entity.getTitle());
        if (updatedRowCount != 1) {
            logger.error("Unable to update Room:{}", entity);
            throw new SQLException("Unable to update room" + entity.getId());
        }
        return new Room(entity.getId(), entity.getTitle());
    }
}
