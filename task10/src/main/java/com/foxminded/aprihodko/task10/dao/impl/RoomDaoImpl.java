package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.RoomDao;
import com.foxminded.aprihodko.task10.models.Room;

@Repository
public class RoomDaoImpl extends AbstractCrudDao<Room, Long> implements RoomDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(RoomDaoImpl.class);

    @Override
    public Optional<Room> findById(Long id) throws SQLException {
        Room room = entityManager.find(Room.class, id);
        return room != null ? Optional.of(room) : Optional.empty();
    }

    @Override
    public List<Room> findAll() throws SQLException {
        TypedQuery<Room> query = entityManager.createQuery("SELECT r FROM Room r", Room.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws SQLException {
        try {
            Room room = findById(id).orElseThrow();
            entityManager.remove(room);
        } catch (Exception e) {
            logger.error("Unable to user room (id = " + id + ")");
            throw new SQLException("Unable to delete room (id = " + id + ")");
        }
    }

    @Override
    public Optional<Room> findByTitle(String name) {
        TypedQuery<Room> query = entityManager.createQuery("From Room WHERE room_title = '" + name + "'", Room.class);
        Room room = query.getSingleResult();
        return room != null ? Optional.of(room) : Optional.empty();
    }

    @Override
    @Transactional
    public Room create(Room entity) throws SQLException {
        entityManager.persist(entity);
        return new Room(entity.getId(), entity.getTitle());
    }

    @Override
    @Transactional
    public Room update(Room entity) throws SQLException {
        Room room = findById(entity.getId()).orElseThrow();
        entityManager.merge(room);
        return new Room(entity.getId(), entity.getTitle());
    }
}
