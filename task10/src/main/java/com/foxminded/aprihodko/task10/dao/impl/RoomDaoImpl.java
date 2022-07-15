package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.RoomDao;
import com.foxminded.aprihodko.task10.dao.mapper.RoomMapper;
import com.foxminded.aprihodko.task10.models.Room;

public class RoomDaoImpl extends AbstractCrudDao<Room, Long> implements RoomDao {

    public static final String FIND_BY_ID = "SELECT * FROM university.rooms WHERE room_id = ?";
    public static final String FIND_ALL = "SELECT * FROM university.rooms";
    public static final String DELETE_BY_ID = "DELETE FROM university.rooms WHERE room_id = ?";
    public static final String FIND_BY_TITLE = "SELECT * FROM university.rooms WHERE room_title = ?";
    public static final String CREATE = "INSERT INTO university.rooms (room_id, room_title) VALUES (?, ?)";
    public static final String UPDATE = "UPDATE university.rooms SET room_title) WHERE room_id = ?)";

    private JdbcTemplate jdbcTemplate;
    private RoomMapper mapper;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new RoomMapper();
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
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public Optional<Room> findByTitle(String name) {
        return jdbcTemplate.query(FIND_BY_TITLE, mapper, name).stream().findFirst();
    }

    @Override
    protected int create(Room entity) throws SQLException {
        return jdbcTemplate.update(CREATE, entity.getId(), entity.getTitle());
    }

    @Override
    protected int update(Room entity, Long id) throws SQLException {
        return jdbcTemplate.update(UPDATE, entity.getTitle(), id);
    }
}
