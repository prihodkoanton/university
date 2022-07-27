package com.foxminded.aprihodko.task10.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.aprihodko.task10.models.Lesson;

@Component
public interface LessonDao extends CrudDao<Lesson, Long> {

    Optional<Lesson> findByDayOfWeek(String dayOfWeek) throws SQLException;

    List<Lesson> findByRoomId(Long id) throws SQLException;

    List<Lesson> findByGroupId(Long id) throws SQLException;

    List<Lesson> findByCourseId(Long id) throws SQLException;

    List<Lesson> findByTeacherId(Long id) throws SQLException;

    List<Lesson> findByTimeSpan(Long timeSpan) throws SQLException;
}
