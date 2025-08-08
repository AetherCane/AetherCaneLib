package com.aethercane.aethercanelib.database.service;

import com.aethercane.aethercanelib.database.model.ConnectionInfo;
import com.aethercane.aethercanelib.database.repository.AbstractRepository;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DatabaseService {

    private JdbcConnectionSource connectionSource;

    public void init(ConnectionInfo connectionInfo) {
        try {
            connectionSource = new JdbcConnectionSource(
                    connectionInfo.getUrl(),
                    connectionInfo.getUsername(),
                    connectionInfo.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerRepository(AbstractRepository repository) {
        repository.init(connectionSource);
    }

    public void close() throws Exception {
        if (connectionSource != null) {
            connectionSource.close();
        }
    }
}
