package com.foxminded.aprihodko.task10.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.foxminded.aprihodko.task10.models.Course;
import static com.foxminded.aprihodko.task10.models.Course.*;

public class CourseMapper implements Mapper<Course> {

    @Override
    public Course apply(ResultSet rs) throws SQLException {
        return new Course(rs.getLong(COURSE_ID), rs.getString(COURSE_NAME), rs.getString(COURSE_DESCRIPTION));
    }
}
