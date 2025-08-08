package com.aethercane.aethercanelib.database.repository.impl;

import com.aethercane.aethercanelib.database.model.DatabaseObject;
import com.aethercane.aethercanelib.database.repository.AbstractRepository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class QueryRepository<V extends DatabaseObject, ID> extends AbstractRepository<V, ID> {

    public QueryRepository(Class<V> entityClass) {
        super(entityClass);
    }

    @Override
    public CompletableFuture<Void> save(V value) {
        return CompletableFuture.runAsync(() -> {
            if (!value.isChanged()) return;
            try {
                dao.createOrUpdate(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            value.setChanged(false);
        });
    }

    @Override
    public CompletableFuture<Void> save(Collection<V> values) {
        return CompletableFuture.runAsync(() -> {
            try {
                dao.callBatchTasks(() -> {
                    for (V value : values) {
                        if (!value.isChanged()) continue;
                        dao.createOrUpdate(value);
                    }
                    return null;
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public CompletableFuture<Void> delete(V value) {
        return CompletableFuture.runAsync(() -> {
            try {
                dao.delete(value);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

//
//    public CompletableFuture<Optional<V>> findById(ID id) {
//        return CompletableFuture.supplyAsync(() -> {
//            try {
//                return Optional.ofNullable(dao.queryForId(id));
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
}
