package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.task10.dao.GroupDao;
import com.foxminded.aprihodko.task10.models.Group;

@Repository
public abstract class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(GroupDaoImpl.class);

//    @Override
//    public Optional<Group> findById(Long id) throws SQLException {
//        Group group = entityManager.find(Group.class, id);
//        return group != null ? Optional.of(group) : Optional.empty();
//    }
//
//    @Override
//    public List<Group> findAll() throws SQLException {
//        TypedQuery<Group> query = entityManager.createQuery("SELECT g FROM Group g", Group.class);
//        return query.getResultList();
//    }

//    @Override
//    @Transactional
//    public void deleteById(Long id) throws SQLException {
//        try {
//            Group group = findById(id).orElseThrow();
//            entityManager.remove(group);
//        } catch (Exception e) {
//            logger.error("Unable to user group (id = " + id + ")");
//            throw new SQLException("Unable to delete group (id = " + id + ")");
//        }
//    }

    @Override
    public Optional<Group> findByName(String name) throws SQLException {
        TypedQuery<Group> query = entityManager.createQuery("FROM Group WHERE group_name = '" + name + "'",
                Group.class);
        Group group = query.getSingleResult();
        return group != null ? Optional.of(group) : Optional.empty();
    }

//    @Override
//    @Transactional
//    public Group create(Group entity) throws SQLException {
//        entityManager.persist(entity);
//        return new Group(entity.getId(), entity.getName());
//    }

//    @Override
//    @Transactional
//    public Group update(Group entity) throws SQLException {
//        Group group = findById(entity.getId()).orElseThrow();
//        entityManager.merge(group);
//        return new Group(entity.getId(), entity.getName());
//    }
}
