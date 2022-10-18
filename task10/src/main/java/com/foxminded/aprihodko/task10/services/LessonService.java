package com.foxminded.aprihodko.task10.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Lesson;

public interface LessonService {

    Optional<Lesson> findById(Long id) throws SQLException;

    List<Lesson> findAll() throws SQLException;

    void deleteById(Long id) throws SQLException;

    Optional<Lesson> findByDayOfWeek(String dayOfWeek) throws SQLException;

    List<Lesson> findByRoomId(Long id) throws SQLException;

    List<Lesson> findByGroupId(Long id) throws SQLException;

    List<Lesson> findByCourseId(Long id) throws SQLException;

    List<Lesson> findByTeacherId(Long id) throws SQLException;

    List<Lesson> findByTimeSpan(int timeSpan) throws SQLException;

    Lesson save(Lesson entity) throws SQLException;
}
