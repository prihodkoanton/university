package com.foxminded.aprihodko.task10.dao.mapper;

import com.foxminded.aprihodko.task10.models.Lesson;
import static com.foxminded.aprihodko.task10.models.Lesson.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper implements RowMapper<Lesson> {

    @Override
    public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong(LESSON_ID);
        DayOfWeek day = DayOfWeek.valueOf(rs.getString(LESSON_DAY_OF_WEEK));
        int time_span = rs.getInt(LESSON_TIME_SPAN);
        Long room_id = rs.getLong(ROOM_REF);
        Long group_id = rs.getLong(GROUP_REF);
        Long course_id = rs.getLong(COURSE_REF);
        Long teacher_id = rs.getLong(TEACHER_REF);
        return new Lesson(id, day, time_span, room_id, group_id, course_id, teacher_id);
    }
}
