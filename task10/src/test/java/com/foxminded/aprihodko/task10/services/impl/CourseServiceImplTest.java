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
import com.foxminded.aprihodko.task10.models.Course;
import com.foxminded.aprihodko.task10.repositories.CourseRepository;
import com.foxminded.aprihodko.task10.services.CourseService;

@SpringBootTest(classes = { CourseServiceImpl.class })
class CourseServiceImplTest extends BaseDaoTest {

	@MockBean
	CourseRepository courseRepository;

	@Autowired
	private CourseService courseServiceImpl;

	@Test
	void shouldFindById() throws SQLException {
		Course course = new Course(100L, "java", "test");
		when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
		Optional<Course> expected = courseRepository.findById(100L);
		Optional<Course> actual = courseServiceImpl.findById(100L);
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindAll() throws SQLException {
		List<Course> courseList = Arrays.asList(new Course(100L, "java", "Java course"),
				new Course(101L, "english", "English course"), new Course(102L, "math", "Math course"));
		when(courseRepository.findAll()).thenReturn(courseList);
		List<Course> expected = courseRepository.findAll();
		List<Course> actual = courseServiceImpl.findAll();
		assertEquals(expected, actual);
	}

	@Test
	void shouldDeleteById() throws SQLException {
		courseServiceImpl.deleteById(100L);
		verify(courseRepository).deleteById(100L);
	}

	@Test
	void shouldNotDeleteById() throws SQLException {
		when(courseRepository.findById(10L)).thenReturn(null);
		doThrow(new EmptyResultDataAccessException(1)).when(courseRepository).deleteById(10L);
		Exception actual = assertThrows(EmptyResultDataAccessException.class, () -> courseServiceImpl.deleteById(10L));
		Exception expected = assertThrows(EmptyResultDataAccessException.class, () -> courseRepository.deleteById(10L));
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindByName() throws SQLException {
		Course course = new Course(100L, "java", "Java course");
		when(courseRepository.findByName(course.getName())).thenReturn(Optional.of(course));
		Optional<Course> actual = courseServiceImpl.findByName("java");
		Optional<Course> expected = courseRepository.findByName("java");
		assertEquals(expected, actual);
	}

	@Test
	void shouldCourse() throws SQLException {
		Course course = new Course(104L, "java", "Java course");
		when(courseRepository.save(course)).thenReturn(course);
		Course expected = courseRepository.save(course);
		Course actual = courseServiceImpl.save(course);
		assertNotNull(actual.getId());
		course.setId(actual.getId());
		assertEquals(expected, actual);
	}
}
