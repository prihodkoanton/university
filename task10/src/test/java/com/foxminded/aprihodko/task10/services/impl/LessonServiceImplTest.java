package com.foxminded.aprihodko.task10.services.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.models.Lesson;
import com.foxminded.aprihodko.task10.repositories.LessonRepository;
import com.foxminded.aprihodko.task10.services.LessonService;

@SpringBootTest(classes = { LessonServiceImpl.class })
class LessonServiceImplTest extends BaseDaoTest {

	@MockBean
	LessonRepository lessonRepository;

	@Autowired
	private LessonService lessonServiceImpl;

	@Test
	void shouldFindById() throws SQLException {
		Lesson lesson = new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L);
		when(lessonRepository.findById(100L)).thenReturn(Optional.of(lesson));
		Optional<Lesson> expected = lessonRepository.findById(100L);
		Optional<Lesson> actual = lessonServiceImpl.findById(100L);
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindAll() throws SQLException {
		List<Lesson> lessons = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L),
				new Lesson(101L, DayOfWeek.TUESDAY, 1, 101L, 101L, 101L, 101L),
				new Lesson(102L, DayOfWeek.WEDNESDAY, 1, 102L, 102L, 102L, 102L));
		when(lessonRepository.findAll()).thenReturn(lessons);
		List<Lesson> expected = lessonRepository.findAll();
		List<Lesson> actual = lessonServiceImpl.findAll();
		assertEquals(expected, actual);
	}

	@Test
	void shouldDeleteById() throws SQLException {
		lessonRepository.deleteById(100L);
		verify(lessonRepository).deleteById(100L);
	}

	@Test
	void shouldNotDeleteById() throws SQLException {
		when(lessonRepository.findById(10L)).thenReturn(null);
		doThrow(new EmptyResultDataAccessException(1)).when(lessonRepository).deleteById(10L);
		Exception actual = assertThrows(EmptyResultDataAccessException.class, () -> lessonServiceImpl.deleteById(10L));
		Exception expected = assertThrows(EmptyResultDataAccessException.class, () -> lessonRepository.deleteById(10L));
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindByDayOfWeek() throws SQLException {
		Lesson lesson = new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L);
		when(lessonRepository.findByDayOfWeek(DayOfWeek.MONDAY.toString())).thenReturn(Optional.of(lesson));
		Optional<Lesson> expected = lessonRepository.findByDayOfWeek(DayOfWeek.MONDAY.toString());
		Optional<Lesson> actual = lessonServiceImpl.findByDayOfWeek(DayOfWeek.MONDAY.toString());
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindByRoomId() throws SQLException {
		List<Lesson> lessons = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
		when(lessonRepository.findByRoomId(100L)).thenReturn(lessons);
		List<Lesson> expected = lessonRepository.findByRoomId(100L);
		List<Lesson> actual = lessonServiceImpl.findByRoomId(100L);
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindByGroupId() throws SQLException {
		List<Lesson> lessons = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
		when(lessonRepository.findByGroupId(100L)).thenReturn(lessons);
		List<Lesson> expected = lessonRepository.findByGroupId(100L);
		List<Lesson> actual = lessonServiceImpl.findByGroupId(100L);
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindCourseId() throws SQLException {
		List<Lesson> lessons = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
		when(lessonRepository.findByCourseId(100L)).thenReturn(lessons);
		List<Lesson> expected = lessonRepository.findByCourseId(100L);
		List<Lesson> actual = lessonServiceImpl.findByCourseId(100L);
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindTeacherId() throws SQLException {
		List<Lesson> lessons = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
		when(lessonRepository.findByTeacherId(100L)).thenReturn(lessons);
		List<Lesson> expected = lessonRepository.findByTeacherId(100L);
		List<Lesson> actual = lessonServiceImpl.findByTeacherId(100L);
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindTimeSpan() throws SQLException {
		List<Lesson> lessons = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
		when(lessonRepository.findByTimeSpan(1)).thenReturn(lessons);
		List<Lesson> expected = lessonRepository.findByTimeSpan(1);
		List<Lesson> actual = lessonServiceImpl.findByTimeSpan(1);
		assertEquals(expected, actual);
	}

	@Test
	void shouldUpdatCourse() throws SQLException {
		Lesson lesson = new Lesson(1L, DayOfWeek.FRIDAY, 1, 1L, 1L, 1L, 1L);
		when(lessonRepository.save(lesson)).thenReturn(lesson);
		Lesson actual = lessonRepository.save(lesson);
		Lesson expected = lessonServiceImpl.save(lesson);
		assertNotNull(actual.getId());
		lesson.setId(actual.getId());
		assertEquals(expected, actual);
	}

}
