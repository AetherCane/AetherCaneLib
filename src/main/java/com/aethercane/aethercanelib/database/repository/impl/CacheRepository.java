package com.aethercane.aethercanelib.database.repository.impl;

import com.aethercane.aethercanelib.database.model.DatabaseObject;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CacheRepository<V extends DatabaseObject, ID> extends QueryRepository<V, ID> {

    private final Map<ID, V> cacheMap = new HashMap<>();

    private final Function<V, ID> primaryKey;

    public CacheRepository(Class<V> entityClass, Function<V, ID> primaryKey) {
        super(entityClass);
        this.primaryKey = primaryKey;
    }

    @Override
    public void init(ConnectionSource connectionSource) {
        super.init(connectionSource);
        try {
            dao.queryForAll().forEach(this::save);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cacheMap.values().forEach(value -> value.setChanged(false));
    }

    public CompletableFuture<Void> saveAll() {
        return super.save(cacheMap.values());
    }

    @Override
    public CompletableFuture<Void> save(V value) {
        cacheMap.put(primaryKey.apply(value), value);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> delete(V value) {
        cacheMap.remove(primaryKey.apply(value));
        return super.delete(value);
    }

    @Override
    public CompletableFuture<Optional<V>> findById(ID id) {
        return CompletableFuture.completedFuture(Optional.ofNullable(cacheMap.get(id)));
    }

    @Override
    public CompletableFuture<Collection<V>> findAll() {
        return CompletableFuture.completedFuture(cacheMap.values());
    }

    public Optional<V> findOneBy(Predicate<V> condition) {
        return cacheMap.values().stream()
                .filter(condition)
                .findFirst();
    }

    public Collection<V> findBy(Predicate<V> condition) {
        return cacheMap.values().stream()
                .filter(condition)
                .collect(Collectors.toUnmodifiableSet());
    }
}
