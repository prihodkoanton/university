package com.foxminded.aprihodko.task10.services.impl;

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
import org.springframework.dao.EmptyResultDataAccessException;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.models.Room;
import com.foxminded.aprihodko.task10.repositories.RoomRepository;
import com.foxminded.aprihodko.task10.services.RoomService;

@SpringBootTest(classes = { RoomServiceImpl.class })
class RoomServiceImplTest extends BaseDaoTest {

	@MockBean
	RoomRepository roomRepository;

	@Autowired
	private RoomService roomServiceImpl;

	@Test
	void shouldFindById() throws SQLException {
		Room room = new Room(1L, "room for java");
		when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
		Optional<Room> expected = roomRepository.findById(1L);
		Optional<Room> actual = roomServiceImpl.findById(1L);
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindAll() throws SQLException {
		List<Room> rooms = Arrays.asList(new Room(1L, "room for java"), new Room(2L, "room for math"));
		when(roomRepository.findAll()).thenReturn(rooms);
		List<Room> expected = roomRepository.findAll();
		List<Room> actual = roomServiceImpl.findAll();
		assertEquals(expected, actual);
	}

	@Test
	void shouldDeleteById() throws SQLException {
		roomServiceImpl.deleteById(100L);
		verify(roomRepository).deleteById(100L);
	}

	@Test
	void shouldNotDeleteById() throws SQLException {
		when(roomRepository.findById(10L)).thenReturn(null);
		doThrow(new EmptyResultDataAccessException(1)).when(roomRepository).deleteById(10L);
		Exception actual = assertThrows(EmptyResultDataAccessException.class, () -> roomServiceImpl.deleteById(10L));
		Exception expected = assertThrows(EmptyResultDataAccessException.class, () -> roomRepository.deleteById(10L));
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindByTitle() throws SQLException {
		Room room = new Room(1L, "room for java");
		when(roomRepository.findByTitle("room for java")).thenReturn(Optional.of(room));
		Optional<Room> expected = roomRepository.findByTitle("room for java");
		Optional<Room> actual = roomServiceImpl.findByTitle("room for java");
		assertEquals(expected, actual);
	}

	@Test
	void shouldUpdateRoom() throws SQLException {
		Room room = new Room(1L, "room for java");
		when(roomRepository.save(room)).thenReturn(room);
		Room expected = roomRepository.save(room);
		Room actual = roomServiceImpl.save(room);
		assertNotNull(actual.getId());
		room.setId(actual.getId());
		assertEquals(expected, actual);
	}
}
