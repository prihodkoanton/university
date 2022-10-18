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

    @Override
    public Optional<Group> findByName(String name) throws SQLException {
        TypedQuery<Group> query = entityManager.createQuery("FROM Group WHERE group_name = '" + name + "'",
                Group.class);
        Group group = query.getSingleResult();
        return group != null ? Optional.of(group) : Optional.empty();
    }
}
