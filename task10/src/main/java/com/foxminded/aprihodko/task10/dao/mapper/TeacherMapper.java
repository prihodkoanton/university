package com.foxminded.aprihodko.task10.dao.mapper;

import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.UserType;

import static com.foxminded.aprihodko.task10.models.Teacher.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TeacherMapper implements RowMapper<Teacher>{

    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Teacher(rs.getLong(USER_ID), rs.getString(USER_NAME), UserType.valueOf(rs.getString(USER_TYPE)),
                rs.getLong(COURSE_REF));
    }
}
