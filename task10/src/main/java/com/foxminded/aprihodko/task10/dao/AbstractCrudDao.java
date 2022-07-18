package com.foxminded.aprihodko.task10.dao;

import java.sql.SQLException;

import com.foxminded.aprihodko.task10.models.Entity;

public abstract class AbstractCrudDao<T extends Entity<K>, K> implements CrudDao<T, K> {

    public T save(T entity, Long id) throws SQLException {
        return entity.getId() == null ? create(entity) : update(entity, id);
    }

    protected abstract T create(T entity) throws SQLException;

    protected abstract T update(T entity, Long id) throws SQLException;
}
