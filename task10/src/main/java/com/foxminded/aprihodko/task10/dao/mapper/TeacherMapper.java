package com.foxminded.aprihodko.task10.dao.mapper;

import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.UserType;

import static com.foxminded.aprihodko.task10.models.Teacher.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements Mapper<Teacher> {

    @Override
    public Teacher apply(ResultSet rs) throws SQLException {
        return new Teacher(rs.getLong(USER_ID), rs.getString(USER_NAME), UserType.valueOf(rs.getString(USER_TYPE)),
                rs.getLong(COURSE_REF));
    }
}
