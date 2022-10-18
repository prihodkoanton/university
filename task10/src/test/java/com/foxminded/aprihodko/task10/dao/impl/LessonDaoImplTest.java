package com.foxminded.aprihodko.task10.dao.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.dao.LessonDao;
import com.foxminded.aprihodko.task10.models.Lesson;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LessonDaoImplTest extends BaseDaoTest {

    @Autowired
    private LessonDao lessonDao;

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/lesson_test_data.sql" })
    void shoudlFindByLessonId() throws SQLException {
        Lesson expected = new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L);
        Lesson actual = lessonDao.findById(100L).orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/lesson_test_data.sql" })
    void shoudlFindAll() throws SQLException {
        List<Lesson> expected = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L),
                new Lesson(101L, DayOfWeek.TUESDAY, 1, 101L, 101L, 101L, 101L),
                new Lesson(102L, DayOfWeek.WEDNESDAY, 1, 102L, 102L, 102L, 102L));
        List<Lesson> actual = lessonDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/lesson_test_data.sql" })
    void shoudlDeleteById() throws SQLException {
        lessonDao.deleteById(100L);
        Optional<Lesson> shouldBeEmty = lessonDao.findById(100L);
        assertTrue(shouldBeEmty.isEmpty());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/lesson_test_data.sql" })
    void shouldNotDeleteById() throws SQLException {
        Exception e = assertThrows(EmptyResultDataAccessException.class, () -> lessonDao.deleteById(10L));
        assertEquals("No class com.foxminded.aprihodko.task10.models.Lesson entity with id 10 exists!", e.getMessage());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/lesson_test_data.sql" })
    void shoudlFindByRoomId() throws SQLException {
        List<Lesson> expected = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
        List<Lesson> actual = lessonDao.findByRoomId(100L);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/lesson_test_data.sql" })
    void shoudlFindByGroupId() throws SQLException {
        List<Lesson> expected = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
        List<Lesson> actual = lessonDao.findByGroupId(100L);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/lesson_test_data.sql" })
    void shoudlFindByCourseId() throws SQLException {
        List<Lesson> expected = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
        List<Lesson> actual = lessonDao.findByCourseId(100L);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/lesson_test_data.sql" })
    void shoudlFindByTeacherId() throws SQLException {
        List<Lesson> expected = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
        List<Lesson> actual = lessonDao.findByTeacherId(100L);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/lesson_test_data.sql" })
    void shoudlFindByTimeSpan() throws SQLException {
        List<Lesson> expected = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L),
                new Lesson(101L, DayOfWeek.TUESDAY, 1, 101L, 101L, 101L, 101L),
                new Lesson(102L, DayOfWeek.WEDNESDAY, 1, 102L, 102L, 102L, 102L));
        List<Lesson> actual = lessonDao.findByTimeSpan(1);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/lesson_test_data.sql" })
    @Transactional
    void shoudlCreateLesson() throws SQLException {
        Lesson lesson = new Lesson(DayOfWeek.FRIDAY, 1, 103L, 103L, 103L, 103L);
        Lesson actual = lessonDao.save(lesson);
        assertNotNull(actual.getId());
        lesson.setId(actual.getId());
        assertEquals(lesson, actual);
    }
}
