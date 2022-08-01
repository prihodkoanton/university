package com.foxminded.aprihodko.task10.dao.services.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.dao.RoomDao;
import com.foxminded.aprihodko.task10.models.Room;
import com.foxminded.aprihodko.task10.services.impl.RoomServiceImpl;

@SpringBootTest(classes = { RoomServiceImpl.class })
class RoomServiceImplTest extends BaseDaoTest {

    @MockBean
    RoomDao roomDao;

    @Autowired
    private RoomServiceImpl roomServiceImpl;

    @Test
    void shouldFindById() throws SQLException {
        Room room = new Room(1L, "room for java");
        when(roomDao.findById(1L)).thenReturn(Optional.of(room));
        Optional<Room> expected = roomDao.findById(1L);
        Optional<Room> actual = roomServiceImpl.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAll() throws SQLException {
        List<Room> rooms = Arrays.asList(new Room(1L, "room for java"), new Room(2L, "room for math"));
        when(roomDao.findAll()).thenReturn(rooms);
        List<Room> expected = roomDao.findAll();
        List<Room> actual = roomServiceImpl.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteById() throws SQLException {
        roomServiceImpl.deleteById(100L);
        verify(roomDao).deleteById(100L);
    }

    @Test
    void shouldNotDeleteById() throws SQLException {
        when(roomDao.findById(10L)).thenReturn(null);
        doThrow(new SQLException()).when(roomDao).deleteById(10L);
        Exception actual = assertThrows(SQLException.class, () -> roomServiceImpl.deleteById(10L));
        Exception expected = assertThrows(SQLException.class, () -> roomDao.deleteById(10L));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByTitle() throws SQLException {
        Room room = new Room(1L, "room for java");
        when(roomDao.findByTitle("room for java")).thenReturn(Optional.of(room));
        Optional<Room> expected = roomDao.findByTitle("room for java");
        Optional<Room> actual = roomServiceImpl.findByTitle("room for java");
        assertEquals(expected, actual);
    }

    @Test
    void shouldCreateRoom() throws SQLException {
        Room room = new Room(1L, "room for java");
        when(roomDao.save(room)).thenReturn(room);
        Room expected = roomDao.save(room);
        Room actual = roomServiceImpl.update(room);
        assertNotNull(actual.getId());
        room.setId(actual.getId());
        assertEquals(expected, actual);
    }
}
