package com.foxminded.aprihodko.task10.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Group;

public interface GroupService {

    Optional<Group> findById(Long id) throws SQLException;

    List<Group> findAll() throws SQLException;

    void deleteById(Long id) throws SQLException;

    Optional<Group> findByName(String name) throws SQLException;

    Group save(Group entity) throws SQLException;
}
