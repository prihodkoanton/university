package com.foxminded.aprihodko.task10.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.foxminded.aprihodko.task10.dao.impl.RoomDaoImpl;
import com.foxminded.aprihodko.task10.models.Room;
import com.foxminded.aprihodko.task10.services.RoomService;

@Service
public class RoomServiceImpl implements RoomService{

    private final RoomDaoImpl roomDaoImpl;

    public RoomServiceImpl(RoomDaoImpl roomDaoImpl) {
        this.roomDaoImpl = roomDaoImpl;
    }
    
    Optional<Room> findById(Long id) throws SQLException {
        return roomDaoImpl.findById(id);
    }
    
    List<Room> findAll() throws SQLException {
        return roomDaoImpl.findAll();
    }
    
    void deleteById(Long id) throws SQLException {
        roomDaoImpl.deleteById(id);
    }
    
    Optional<Room> findByTitle(String name) {
        return roomDaoImpl.findByTitle(name);
    }
    
    Room update(Room entity, Long id) throws SQLException {
        return roomDaoImpl.save(entity, id);
    }
}
