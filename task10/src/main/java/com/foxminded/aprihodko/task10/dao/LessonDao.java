package com.foxminded.aprihodko.task10.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Lesson;

public interface LessonDao extends CrudDao<Lesson, Long> {

    Optional<Lesson> findByDayOfWeek(Connection connection, String dayOfWeek) throws SQLException;

    List<Lesson> findByRoomId(Connection connection, Long id) throws SQLException;

    List<Lesson> findByGroupId(Connection connection, Long id) throws SQLException;

    List<Lesson> findByCourseId(Connection connection, Long id) throws SQLException;

    List<Lesson> findByTeacherId(Connection connection, Long id) throws SQLException;

    Optional<Lesson> findByTimeSpan(Connection connection, Long timeSpan) throws SQLException;
}
