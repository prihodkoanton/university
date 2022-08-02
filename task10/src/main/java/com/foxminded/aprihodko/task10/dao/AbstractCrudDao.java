package com.foxminded.aprihodko.task10.dao;

import java.sql.SQLException;

import com.foxminded.aprihodko.task10.models.Entity;

public abstract class AbstractCrudDao<T extends Entity<K>, K> implements CrudDao<T, K> {

    @Override
    public T save(T entity) throws SQLException {
        return entity.getId() == null ? create(entity) : update(entity);
    }

    public abstract T create(T entity) throws SQLException;

    public abstract T update(T entity) throws SQLException;
}
