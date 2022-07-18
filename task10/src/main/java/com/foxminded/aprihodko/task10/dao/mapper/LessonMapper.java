package com.foxminded.aprihodko.task10.dao.mapper;

import com.foxminded.aprihodko.task10.models.Lesson;
import static com.foxminded.aprihodko.task10.models.Lesson.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;

import org.springframework.jdbc.core.RowMapper;

public class LessonMapper implements RowMapper<Lesson> {

    @Override
    public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Lesson(rs.getLong(LESSON_ID), DayOfWeek.valueOf(rs.getString(LESSON_DAY_OF_WEEK)),
                rs.getInt(LESSON_TIME_SPAN), rs.getLong(ROOM_REF), rs.getLong(GROUP_REF), rs.getLong(COURSE_REF),
                rs.getLong(TEACHER_REF));
    }
}
