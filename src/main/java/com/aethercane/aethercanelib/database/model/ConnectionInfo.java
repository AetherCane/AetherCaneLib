package com.aethercane.aethercanelib.database.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class ConnectionInfo {

    private final String url;

    private String username;
    private String password;

    public ConnectionInfo(DriverType driverType, String host, int port, String database,
                          boolean useSSL, String username, String password) {
        this.url = driverType.formatURL(host, port, database, useSSL);
        this.username = username;
        this.password = password;
    }

    public ConnectionInfo(DriverType driverType, String dataFolderPath, String databaseFilePath) {
        this.url = driverType.formatURL(dataFolderPath, databaseFilePath);
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public enum DriverType {
        SQLITE("jdbc:sqlite:" + new File("%s", "%s") + ".db"),
        MYSQL("jdbc:mysql://%s:%s/%s?useSSL=%s");

        private final String urlTemplate;

        DriverType(String urlTemplate) {
            this.urlTemplate = urlTemplate;
        }

        public String formatURL(Object... arguments) {
            return urlTemplate.formatted(arguments);
        }
    }
}
