package com.foxminded.aprihodko.task10.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Entity;

public interface CrudDao<T extends Entity<K>, K> {

    Optional<T> findById(K id) throws SQLException;

    List<T> findAll() throws SQLException;

    int save(T enity, Long id) throws SQLException;

    void deleteById(K id) throws SQLException;
}
