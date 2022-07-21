package com.foxminded.aprihodko.task10.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.DayOfWeek;
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
import com.foxminded.aprihodko.task10.dao.LessonDao;
import com.foxminded.aprihodko.task10.models.Lesson;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LessonDaoImplTest extends BaseDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private LessonDao lessonDao;

    @PostConstruct
    void init() {
        lessonDao = new LessonDaoImpl(jdbcTemplate);
    }

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
        List<Lesson> actual = lessonDao.findByTimeSpan(1L);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/lesson_test_data.sql" })
    void shoudlCreateLesson() throws SQLException {
        Lesson expected = new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L);
        Lesson actual = lessonDao.save(expected, 100L);
        assertNotNull(actual.getId());
        expected.setId(actual.getId());
        assertEquals(expected, actual);
    }
}
