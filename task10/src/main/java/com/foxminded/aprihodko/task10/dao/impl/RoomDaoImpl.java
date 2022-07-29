package com.foxminded.aprihodko.task10.dao.impl;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.RoomDao;
import com.foxminded.aprihodko.task10.dao.mapper.RoomMapper;
import com.foxminded.aprihodko.task10.models.Room;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomDaoImpl extends AbstractCrudDao<Room, Long> implements RoomDao {

    public static final String FIND_BY_ID = "SELECT * FROM university.rooms WHERE room_id = ?";
    public static final String FIND_ALL = "SELECT * FROM university.rooms";
    public static final String DELETE_BY_ID = "DELETE FROM university.rooms WHERE room_id = ?";
    public static final String FIND_BY_TITLE = "SELECT * FROM university.rooms WHERE room_title = ?";
    public static final String CREATE = "INSERT INTO university.rooms (room_id, room_title) VALUES (?, ?)";
    public static final String UPDATE = "UPDATE university.rooms SET room_title = ? WHERE room_id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final RoomMapper mapper;

    public RoomDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        mapper = new RoomMapper();
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
        if (jdbcTemplate.update(DELETE_BY_ID, id) != 1) {
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
        if (jdbcTemplate.update(CREATE, entity.getId(), entity.getTitle()) != 1) {
            throw new SQLException("Unable to retrieve id" + entity.getId());
        }
        jdbcTemplate.update(CREATE, entity.getId(), entity.getTitle());
        return entity;
    }

    @Override
    public Room update(Room entity, Long id) throws SQLException {
        if (jdbcTemplate.update(CREATE, entity.getId(), entity.getTitle()) != 1) {
            throw new SQLException("Unable to update room" + entity.getId());
        }
        jdbcTemplate.update(UPDATE, entity.getTitle(), id);
        return entity;
    }
}
