package com.foxminded.aprihodko.task10.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.dao.RoomDao;
import com.foxminded.aprihodko.task10.models.Room;
import com.foxminded.aprihodko.task10.services.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomDao roomDao;

    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Room> findById(Long id) throws SQLException {
        return roomDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> findAll() throws SQLException {
        return roomDao.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws SQLException {
        roomDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Room> findByTitle(String name) {
        return roomDao.findByTitle(name);
    }

    @Override
    @Transactional
    public Room save(Room entity) throws SQLException {
        return roomDao.save(entity);
    }
}
