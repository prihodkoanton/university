package com.foxminded.aprihodko.task10.dao.mapper;

import com.foxminded.aprihodko.task10.models.Group;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.foxminded.aprihodko.task10.models.Group.GROUP_ID;
import static com.foxminded.aprihodko.task10.models.Group.GROUP_NAME;

@Component
public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Group(rs.getLong(GROUP_ID), rs.getString(GROUP_NAME));
    }
}
