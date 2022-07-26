package com.foxminded.aprihodko.task10.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foxminded.aprihodko.task10.models.Course;
import static com.foxminded.aprihodko.task10.models.Course.*;

@Component
public class CourseMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Course(rs.getLong(COURSE_ID), rs.getString(COURSE_NAME), rs.getString(COURSE_DESCRIPTION));
    }
}
