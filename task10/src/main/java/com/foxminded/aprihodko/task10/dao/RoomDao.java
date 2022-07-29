package com.foxminded.aprihodko.task10.dao;

import com.foxminded.aprihodko.task10.models.Room;

import java.util.Optional;


public interface RoomDao extends CrudDao<Room, Long> {

    Optional<Room> findByTitle(String name);
}
