package com.foxminded.aprihodko.task10.dao.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.dao.CourseDao;
import com.foxminded.aprihodko.task10.models.Course;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseDaoImplTest extends BaseDaoTest {

    @Autowired
    CourseDao courseDao;

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/course_test_data.sql" })
    void shouldFindById() throws SQLException {
        List<Course> actual1 = courseDao.findAll();
        Course expected = new Course(100L, "java", "Java course");
        Course actual = courseDao.findById(100L).orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/course_test_data.sql" })
    void shouldFindAll() throws SQLException {
        List<Course> expected = Arrays.asList(new Course(100L, "java", "Java course"),
                new Course(101L, "english", "English course"), new Course(102L, "math", "Math course"));
        List<Course> actual = courseDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/course_test_data.sql" })
    void shouldNotDeleteById() throws SQLException {
        Exception e = assertThrows(NoSuchElementException.class, () -> courseDao.deleteById(10L));
        assertEquals("No value present", e.getMessage());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/course_test_data.sql" })
    void shouldDeleteById() throws SQLException {
        courseDao.deleteById(100L);
        Optional<Course> shouldBeEmpty = courseDao.findById(100L);
        assertTrue(shouldBeEmpty.isEmpty());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/course_test_data.sql" })
    void shouldFindByName() throws SQLException {
        Course expected = new Course(100L, "java", "Java course");
        Course actual = courseDao.findByName("java").orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/course_test_data.sql" })
    void shouldFindByDescription() throws SQLException {
        Course expected = new Course(100L, "java", "Java course");
        Course actual = courseDao.findByDescription("Java course").orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/course_test_data.sql" })
    @Transactional
    void shouldCreateCourse() throws SQLException {
        Course expected = new Course("java", "Java course");
        Course actual = courseDao.save(expected);
        assertNotNull(actual.getId());
        expected.setId(actual.getId());
        assertEquals(expected, actual);
    }
}
