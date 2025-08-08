package com.aethercane.aethercanelib.database.repository;

import com.aethercane.aethercanelib.database.model.DatabaseObject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractRepository<V extends DatabaseObject, ID> {

    private final Class<V> entityClass;

    protected Dao<V, ID> dao;

    protected AbstractRepository(Class<V> entityClass) {
        this.entityClass = entityClass;
    }

    public void init(ConnectionSource connectionSource) {
        try {
            dao = DaoManager.createDao(connectionSource, entityClass);
            TableUtils.createTableIfNotExists(connectionSource, entityClass);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract CompletableFuture<Void> save(V value);
    public abstract CompletableFuture<Void> save(Collection<V> value);
    public abstract CompletableFuture<Void> delete(V value);
    public abstract CompletableFuture<Optional<V>> findById(ID id);
}
