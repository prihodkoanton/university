package com.foxminded.aprihodko.task10.dao.datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.Driver;

public class SimpleDatasource implements Datasource {

    public static final String DRIVER_CLASS = "datasource.driver-class";
    public static final String JDBC_URL = "datasource.jdbc-url";
    public static final String USERNAME = "datasource.username";
    public static final String PASSWORD = "datasource.password";

    private final Driver driver;
    private final String jdbcUrl;
    private final String username;
    private final String password;

    public SimpleDatasource(Properties properties) {
        String className = properties.getProperty(DRIVER_CLASS);
        this.jdbcUrl = properties.getProperty(JDBC_URL);
        this.username = properties.getProperty(USERNAME);
        this.password = properties.getProperty(PASSWORD);
        try {
            @SuppressWarnings("unchecked")
            Class<Driver> clazz = (Class<Driver>) Class.forName(className);
            driver = clazz.newInstance();
            DriverManager.registerDriver(driver);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
