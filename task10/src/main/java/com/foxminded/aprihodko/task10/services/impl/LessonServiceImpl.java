package com.foxminded.aprihodko.task10.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.dao.LessonDao;
import com.foxminded.aprihodko.task10.models.Lesson;
import com.foxminded.aprihodko.task10.services.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonDao lessonDao;

    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Transactional
    public Optional<Lesson> findById(Long id) throws SQLException {
        return lessonDao.findById(id);
    }

    @Transactional
    public List<Lesson> findAll() throws SQLException {
        return lessonDao.findAll();
    }

    @Transactional
    public void deleteById(Long id) throws SQLException {
        lessonDao.deleteById(id);
    }

    @Transactional
    public Optional<Lesson> findByDayOfWeek(String dayOfWeek) throws SQLException {
        return lessonDao.findByDayOfWeek(dayOfWeek);
    }

    @Transactional
    public List<Lesson> findByRoomId(Long id) throws SQLException {
        return lessonDao.findByRoomId(id);
    }

    @Transactional
    public List<Lesson> findByGroupId(Long id) throws SQLException {
        return lessonDao.findByGroupId(id);
    }

    @Transactional
    public List<Lesson> findByCourseId(Long id) throws SQLException {
        return lessonDao.findByCourseId(id);
    }

    @Transactional
    public List<Lesson> findByTeacherId(Long id) throws SQLException {
        return lessonDao.findByTeacherId(id);
    }

    @Transactional
    public List<Lesson> findByTimeSpan(Long timeSpan) throws SQLException {
        return lessonDao.findByTimeSpan(timeSpan);
    }

    @Transactional
    public Lesson update(Lesson entity) throws SQLException {
        return lessonDao.save(entity);
    }
}
