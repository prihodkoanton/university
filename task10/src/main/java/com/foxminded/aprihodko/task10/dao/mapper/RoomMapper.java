package com.foxminded.aprihodko.task10.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.aprihodko.task10.models.Room;
import static com.foxminded.aprihodko.task10.models.Room.*;

public class RoomMapper implements RowMapper<Room>{

    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Room(rs.getLong(ROOM_ID), rs.getString(ROOM_TITLE));
    }
}
