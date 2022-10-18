package com.foxminded.aprihodko.task10.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxminded.aprihodko.task10.models.Lesson;

public interface LessonDao extends JpaRepository<Lesson, Long> {

    Optional<Lesson> findByDayOfWeek(String dayOfWeek) throws SQLException;

    List<Lesson> findByRoomId(Long id) throws SQLException;

    List<Lesson> findByGroupId(Long id) throws SQLException;

    List<Lesson> findByCourseId(Long id) throws SQLException;

    List<Lesson> findByTeacherId(Long id) throws SQLException;

    List<Lesson> findByTimeSpan(int timeSpan) throws SQLException;
}
