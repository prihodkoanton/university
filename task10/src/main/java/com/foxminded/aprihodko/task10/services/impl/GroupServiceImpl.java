package com.foxminded.aprihodko.task10.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.dao.GroupDao;
import com.foxminded.aprihodko.task10.models.Group;
import com.foxminded.aprihodko.task10.services.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Transactional
    public Optional<Group> findById(Long id) throws SQLException {
        return groupDao.findById(id);
    }

    @Transactional
    public List<Group> findAll() throws SQLException {
        return groupDao.findAll();
    }

    @Transactional
    public void deleteById(Long id) throws SQLException {
        groupDao.deleteById(id);
    }

    @Transactional
    public Optional<Group> findByName(String name) throws SQLException {
        return groupDao.findByName(name);
    }

    @Transactional
    public Group update(Group entity, Long id) throws SQLException {
        return groupDao.save(entity, id);
    }
}
