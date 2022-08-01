package com.foxminded.aprihodko.task10.dao.services.impl;

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

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.dao.LessonDao;
import com.foxminded.aprihodko.task10.models.Lesson;
import com.foxminded.aprihodko.task10.services.impl.LessonServiceImpl;

@SpringBootTest(classes = { LessonServiceImpl.class })
class LessonServiceImplTest extends BaseDaoTest {

    @MockBean
    LessonDao lessonDao;

    @Autowired
    private LessonServiceImpl lessonServiceImpl;

    @Test
    void shouldFindById() throws SQLException {
        Lesson lesson = new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L);
        when(lessonDao.findById(100L)).thenReturn(Optional.of(lesson));
        Optional<Lesson> expected = lessonDao.findById(100L);
        Optional<Lesson> actual = lessonServiceImpl.findById(100L);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAll() throws SQLException {
        List<Lesson> lessons = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L),
                new Lesson(101L, DayOfWeek.TUESDAY, 1, 101L, 101L, 101L, 101L),
                new Lesson(102L, DayOfWeek.WEDNESDAY, 1, 102L, 102L, 102L, 102L));
        when(lessonDao.findAll()).thenReturn(lessons);
        List<Lesson> expected = lessonDao.findAll();
        List<Lesson> actual = lessonServiceImpl.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteById() throws SQLException {
        lessonDao.deleteById(100L);
        verify(lessonDao).deleteById(100L);
    }

    @Test
    void shouldNotDeleteById() throws SQLException {
        when(lessonDao.findById(10L)).thenReturn(null);
        doThrow(new SQLException()).when(lessonDao).deleteById(10L);
        Exception actual = assertThrows(SQLException.class, () -> lessonServiceImpl.deleteById(10L));
        Exception expected = assertThrows(SQLException.class, () -> lessonDao.deleteById(10L));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByDayOfWeek() throws SQLException {
        Lesson lesson = new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L);
        when(lessonDao.findByDayOfWeek(DayOfWeek.MONDAY.toString())).thenReturn(Optional.of(lesson));
        Optional<Lesson> expected = lessonDao.findByDayOfWeek(DayOfWeek.MONDAY.toString());
        Optional<Lesson> actual = lessonServiceImpl.findByDayOfWeek(DayOfWeek.MONDAY.toString());
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByRoomId() throws SQLException {
        List<Lesson> lessons = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
        when(lessonDao.findByRoomId(100L)).thenReturn(lessons);
        List<Lesson> expected = lessonDao.findByRoomId(100L);
        List<Lesson> actual = lessonServiceImpl.findByRoomId(100L);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByGroupId() throws SQLException {
        List<Lesson> lessons = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
        when(lessonDao.findByGroupId(100L)).thenReturn(lessons);
        List<Lesson> expected = lessonDao.findByGroupId(100L);
        List<Lesson> actual = lessonServiceImpl.findByGroupId(100L);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindCourseId() throws SQLException {
        List<Lesson> lessons = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
        when(lessonDao.findByCourseId(100L)).thenReturn(lessons);
        List<Lesson> expected = lessonDao.findByCourseId(100L);
        List<Lesson> actual = lessonServiceImpl.findByCourseId(100L);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindTeacherId() throws SQLException {
        List<Lesson> lessons = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
        when(lessonDao.findByTeacherId(100L)).thenReturn(lessons);
        List<Lesson> expected = lessonDao.findByTeacherId(100L);
        List<Lesson> actual = lessonServiceImpl.findByTeacherId(100L);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindTimeSpan() throws SQLException {
        List<Lesson> lessons = Arrays.asList(new Lesson(100L, DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L));
        when(lessonDao.findByTimeSpan(1L)).thenReturn(lessons);
        List<Lesson> expected = lessonDao.findByTimeSpan(1L);
        List<Lesson> actual = lessonServiceImpl.findByTimeSpan(1L);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdatCourse() throws SQLException {
        Lesson lesson = new Lesson(1L, DayOfWeek.FRIDAY, 1, 1L, 1L, 1L, 1L);
        when(lessonDao.save(lesson)).thenReturn(lesson);
        Lesson actual = lessonDao.save(lesson);
        Lesson expected = lessonServiceImpl.update(lesson);
        assertNotNull(actual.getId());
        lesson.setId(actual.getId());
        assertEquals(expected, actual);
    }

}
