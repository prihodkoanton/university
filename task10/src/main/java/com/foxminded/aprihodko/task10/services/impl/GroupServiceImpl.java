package com.foxminded.aprihodko.task10.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.dao.impl.GroupDaoImpl;
import com.foxminded.aprihodko.task10.models.Group;
import com.foxminded.aprihodko.task10.services.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDaoImpl groupDaoImpl;

    public GroupServiceImpl(GroupDaoImpl groupDaoImpl) {
        this.groupDaoImpl = groupDaoImpl;
    }

    @Transactional
    Optional<Group> findById(Long id) throws SQLException {
        return groupDaoImpl.findById(id);
    }

    @Transactional
    List<Group> findAll() throws SQLException {
        return groupDaoImpl.findAll();
    }

    @Transactional
    void deleteById(Long id) throws SQLException {
        groupDaoImpl.deleteById(id);
    }

    @Transactional
    Optional<Group> findByName(String name) throws SQLException {
        return groupDaoImpl.findByName(name);
    }

    @Transactional
    Group update(Group entity, Long id) throws SQLException {
        return groupDaoImpl.save(entity, id);
    }
}
