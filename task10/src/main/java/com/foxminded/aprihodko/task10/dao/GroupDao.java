package com.foxminded.aprihodko.task10.dao;

import com.foxminded.aprihodko.task10.models.Group;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Component
public interface GroupDao extends CrudDao<Group, Long> {

    Optional<Group> findByName(String name) throws SQLException;
}
