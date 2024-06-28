package com.Superlee.HR.Backend.DataAccess;

import java.util.List;
import java.sql.Connection;

abstract class Controller<T> {
    protected Connection conn;
    protected final String path = "jdbc:sqlite:src/main/resources/HR.db";

    abstract boolean insert(DTO dto);

    abstract boolean update(DTO dto);

    abstract boolean delete(DTO dto);

    abstract List<T> loadAll();
}
