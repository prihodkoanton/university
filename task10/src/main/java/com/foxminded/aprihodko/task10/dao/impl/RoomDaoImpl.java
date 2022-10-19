package com.foxminded.aprihodko.task10.dao.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.task10.dao.RoomDao;
import com.foxminded.aprihodko.task10.models.Room;

@Repository
public abstract class RoomDaoImpl implements RoomDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(RoomDaoImpl.class);

    @Override
    public Optional<Room> findByTitle(String name) {
        TypedQuery<Room> query = entityManager.createQuery("From Room WHERE room_title = '" + name + "'", Room.class);
        Room room = query.getSingleResult();
        return room != null ? Optional.of(room) : Optional.empty();
    }
}
