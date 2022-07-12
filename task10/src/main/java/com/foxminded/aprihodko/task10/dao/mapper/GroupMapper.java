package com.foxminded.aprihodko.task10.dao.mapper;

import com.foxminded.aprihodko.task10.models.Group;
import static com.foxminded.aprihodko.task10.models.Group.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements Mapper<Group>{

    @Override
    public Group apply(ResultSet rs) throws SQLException {
        return new Group(rs.getLong(GROUP_ID), rs.getString(GROUP_NAME));
    }
}
