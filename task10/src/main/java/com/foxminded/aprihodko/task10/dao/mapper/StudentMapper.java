package com.foxminded.aprihodko.task10.dao.mapper;

import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.UserType;

import static com.foxminded.aprihodko.task10.models.Student.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(rs.getLong(USER_ID), rs.getString(USER_NAME), UserType.valueOf(rs.getString(USER_TYPE)),
                rs.getLong(GROUP_REF));
    }
}
