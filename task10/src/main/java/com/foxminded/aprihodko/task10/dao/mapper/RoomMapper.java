package com.foxminded.aprihodko.task10.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.foxminded.aprihodko.task10.models.Room;
import static com.foxminded.aprihodko.task10.models.Room.*;

public class RoomMapper implements Mapper<Room> {

    @Override
    public Room apply(ResultSet rs) throws SQLException {
        return new Room(rs.getLong(ROOM_ID), rs.getString(ROOM_TITLE));
    }
}
