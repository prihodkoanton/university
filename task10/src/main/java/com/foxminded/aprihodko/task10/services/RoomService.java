package com.foxminded.aprihodko.task10.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Room;

public interface RoomService {

    Optional<Room> findById(Long id) throws SQLException;

    List<Room> findAll() throws SQLException;

    public void deleteById(Long id) throws SQLException;

    public Optional<Room> findByTitle(String name);

    public Room save(Room entity) throws SQLException;
}
