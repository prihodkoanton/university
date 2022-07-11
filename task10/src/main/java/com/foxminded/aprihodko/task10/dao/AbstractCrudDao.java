package com.foxminded.aprihodko.task10.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.foxminded.aprihodko.task10.models.Entity;

public abstract class AbstractCrudDao<T extends Entity<K>, K> implements CrudDao<T, K> {

    public T save(Connection connection, T entity) throws SQLException {
        return entity.getId() == null ? create(connection, entity) : update(connection, entity);
    }

    protected abstract T create(Connection connection, T entity) throws SQLException;

    protected abstract T update(Connection connection, T entity) throws SQLException;
}
