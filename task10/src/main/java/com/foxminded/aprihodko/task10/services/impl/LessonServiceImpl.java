package com.foxminded.aprihodko.task10.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.dao.impl.LessonDaoImpl;
import com.foxminded.aprihodko.task10.models.Lesson;
import com.foxminded.aprihodko.task10.services.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonDaoImpl lessonDaoImpl;

    public LessonServiceImpl(LessonDaoImpl lessonDaoImpl) {
        this.lessonDaoImpl = lessonDaoImpl;
    }

    @Transactional
    Optional<Lesson> findById(Long id) throws SQLException {
        return lessonDaoImpl.findById(id);
    }

    @Transactional
    List<Lesson> findAll() throws SQLException {
        return lessonDaoImpl.findAll();
    }

    @Transactional
    void deleteById(Long id) throws SQLException {
        lessonDaoImpl.deleteById(id);
    }

    @Transactional
    Optional<Lesson> findByDayOfWeek(String dayOfWeek) throws SQLException {
        return lessonDaoImpl.findByDayOfWeek(dayOfWeek);
    }
    
    @Transactional
    List<Lesson> findByRoomId(Long id) throws SQLException {
        return lessonDaoImpl.findByRoomId(id);
    }
    
    @Transactional
    List<Lesson> findByGroupId(Long id) throws SQLException {
        return lessonDaoImpl.findByGroupId(id);
    }

    @Transactional
    List<Lesson> findByCourseId(Long id) throws SQLException {
        return lessonDaoImpl.findByCourseId(id);
    }

    @Transactional
    List<Lesson> findByTeacherId(Long id) throws SQLException {
        return lessonDaoImpl.findByTeacherId(id);
    }

    @Transactional
    List<Lesson> findByTimeSpan(Long timeSpan) throws SQLException {
        return lessonDaoImpl.findByTimeSpan(timeSpan);
    }

    @Transactional
    Lesson update(Lesson entity, Long id) throws SQLException {
        return lessonDaoImpl.save(entity, id);
    }
}
