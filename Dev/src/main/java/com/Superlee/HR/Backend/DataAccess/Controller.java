package com.Superlee.HR.Backend.DataAccess;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.util.Objects;

abstract class Controller<T extends DTO> {
    protected final String path = "jdbc:sqlite:src/main/resources/HR.db";
    protected Connection conn;
    protected T dto;

    Controller(T dto) {
        Objects.requireNonNull(dto);
        this.dto = dto;
    }

    abstract boolean insert();

    abstract boolean update();

    abstract boolean delete();

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
