package com.foxminded.aprihodko.task10.dao.mapper;

import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;

import static com.foxminded.aprihodko.task10.models.User.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    @Override
    public User apply(ResultSet rs) throws SQLException {
        return new User(rs.getLong(USER_ID), rs.getString(USER_NAME), UserType.valueOf(rs.getString(USER_TYPE)));
    }
}
