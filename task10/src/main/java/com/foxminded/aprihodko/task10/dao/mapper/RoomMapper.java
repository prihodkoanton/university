package com.foxminded.aprihodko.task10.dao.mapper;

import com.foxminded.aprihodko.task10.models.Room;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.foxminded.aprihodko.task10.models.Room.ROOM_ID;
import static com.foxminded.aprihodko.task10.models.Room.ROOM_TITLE;

@Component
public class RoomMapper implements RowMapper<Room> {

    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Room(rs.getLong(ROOM_ID), rs.getString(ROOM_TITLE));
    }
}
