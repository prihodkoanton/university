package com.foxminded.aprihodko.task10.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.dao.RoomDao;
import com.foxminded.aprihodko.task10.models.Room;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoomDaoImplTest extends BaseDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RoomDao roomDao;

    @PostConstruct
    void init() {
        roomDao = new RoomDaoImpl(jdbcTemplate);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/room_test_data.sql" })
    void shouldFindRoomById() throws SQLException {
        Room expected = new Room(100L, "room for Java");
        Room actual = roomDao.findById(100L).orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/room_test_data.sql" })
    void shouldFindAll() throws SQLException {
        List<Room> expected = Arrays.asList(new Room(100L, "room for Java"));
        List<Room> actual = roomDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/room_test_data.sql" })
    void shouldDeleteById() throws SQLException {
        roomDao.deleteById(100L);
        Optional<Room> shouldBeEmpty = roomDao.findById(100L);
        assertTrue(shouldBeEmpty.isEmpty());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/room_test_data.sql" })
    void shouldFindByTittle() throws SQLException {
        Room expected = new Room(100L, "room for Java");
        Room actual = roomDao.findByTitle("room for Java").orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/room_test_data.sql" })
    void shouldCreateRoom() throws SQLException {
        Room expected = new Room(100L, "room for Java");
        Room actual = roomDao.save(expected, 100L);
        assertNotNull(actual.getId());
        expected.setId(actual.getId());
        assertEquals(expected, actual);
    }
}
