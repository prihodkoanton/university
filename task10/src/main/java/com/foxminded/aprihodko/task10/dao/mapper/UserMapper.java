package com.foxminded.aprihodko.task10.dao.mapper;

import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.foxminded.aprihodko.task10.models.Student.GROUP_REF;
import static com.foxminded.aprihodko.task10.models.Teacher.COURSE_REF;
import static com.foxminded.aprihodko.task10.models.User.*;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserType type = UserType.valueOf(rs.getString(USER_TYPE));
        Long id = rs.getLong(USER_ID);
        String name = rs.getString(USER_NAME);
        if (type == UserType.STUDENT) {
            return new Student(id, name, rs.getLong(GROUP_REF));
        } else if (type == UserType.TEACHER) {
            return new Teacher(id, name, rs.getLong(COURSE_REF));
        } else {
            return new User(id, name, type);
        }
    }
}
