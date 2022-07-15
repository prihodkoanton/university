package com.foxminded.aprihodko.task10.dao;

import java.sql.SQLException;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Group;

public interface GroupDao extends CrudDao<Group, Long> {
    Optional<Group> findByName(String name) throws SQLException;
}
