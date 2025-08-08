package com.aethercane.aethercanelib.database.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConnectionInfo {

    private final String url;
    private final String username;
    private final String password;

    public ConnectionInfo(DriverType driverType, String host, String port, String database,
                          boolean useSSL, String username, String password) {
        this.url = driverType.formatURL(host, port, database, useSSL);
        this.username = username;
        this.password = password;
    }

    public ConnectionInfo(DriverType driverType, String databaseFilePath, String password, String username) {
        this.url = driverType.formatURL(databaseFilePath);
        this.password = password;
        this.username = username;
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
        SQLITE("jdbc:sqlite:%s"),
        MYSQL("jdbc:mysql://%s:%s/%s?useSSL=%s");

        private String urlTemplate;

        DriverType(String urlTemplate) {
            this.urlTemplate = urlTemplate;
        }

        public String formatURL(Object... arguments) {
            return urlTemplate.formatted(arguments);
        }
    }
}
