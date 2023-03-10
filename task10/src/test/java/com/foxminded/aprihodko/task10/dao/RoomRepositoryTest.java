package com.foxminded.aprihodko.task10.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.models.Room;
import com.foxminded.aprihodko.task10.repositories.RoomRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoomRepositoryTest extends BaseDaoTest {

	@Autowired
	private RoomRepository roomRepository;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/room_test_data.sql" })
	void shouldFindRoomById() throws SQLException {
		Room expected = new Room(100L, "room for Java");
		Room actual = roomRepository.findById(100L).orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/room_test_data.sql" })
	void shouldFindAll() throws SQLException {
		List<Room> expected = Arrays.asList(new Room(100L, "room for Java"));
		List<Room> actual = roomRepository.findAll();
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/room_test_data.sql" })
	void shouldNotDeleteById() throws SQLException {
		roomRepository.deleteById(100L);
		Optional<Room> shouldBeEmpty = roomRepository.findById(100L);
		assertTrue(shouldBeEmpty.isEmpty());
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/room_test_data.sql" })
	void shouldDeleteById() throws SQLException {
		roomRepository.deleteById(100L);
		Optional<Room> shouldBeEmpty = roomRepository.findById(100L);
		assertTrue(shouldBeEmpty.isEmpty());
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/room_test_data.sql" })
	void shouldFindByTittle() throws SQLException {
		Room expected = new Room(100L, "room for Java");
		Room actual = roomRepository.findByTitle("room for Java").orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/room_test_data.sql" })
	@Transactional
	void shouldCreateRoom() throws SQLException {
		Room expected = new Room("room for Java");
		Room actual = roomRepository.save(expected);
		assertNotNull(actual.getId());
		expected.setId(actual.getId());
		assertEquals(expected, actual);
	}
}
