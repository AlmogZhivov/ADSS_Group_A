package com.Superlee.HR.Backend.DataAccess;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

abstract class Controller<T> {
    protected final String path = "jdbc:sqlite:src/main/resources/HR.db";
    protected Connection conn;

    abstract boolean insert(DTO dto);

    abstract boolean update(DTO dto);

    abstract boolean delete(DTO dto);

    abstract List<T> loadAll();

    protected void connect() throws SQLException {
        conn = DriverManager.getConnection(path);
    }

    protected void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
