package com.foxminded.aprihodko.task10.dao;

import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Room;

public interface RoomDao extends CrudDao<Room, Long> {

    Optional<Room> findByTitle(String name);
}