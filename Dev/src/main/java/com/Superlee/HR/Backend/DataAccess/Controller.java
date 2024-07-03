package com.Superlee.HR.Backend.DataAccess;

import java.sql.*;
import java.util.List;
import java.util.Objects;

abstract class Controller<T extends DTO> {
    protected final String PATH_DEFAULT = "jdbc:sqlite:src/main/resources/HR.db";
    protected final String PATH_TEST_DEFAULT = "jdbc:sqlite:src/main/resources/HR_Test.db";
    protected String path;
    protected Connection conn;
    protected T dto;

    Controller(T dto) {
        Objects.requireNonNull(dto);
        this.dto = dto;
        path = PATH_DEFAULT;
    }

    abstract boolean insert();

    abstract boolean update();

    abstract boolean delete();

    boolean deleteAll() {
        try {
            connect();

            String deleteWorkerAvailability = "DELETE FROM WorkerAvailability;";
            String deleteWorkerRoles = "DELETE FROM WorkerRoles;";
            String deleteShiftAvailableWorkers = "DELETE FROM ShiftAvailableWorkers;";
            String deleteShiftAssignedWorkers = "DELETE FROM ShiftAssignedWorkers;";
            String deleteShiftRoles = "DELETE FROM ShiftRoles;";
            String deleteWorkerShifts = "DELETE FROM WorkerShifts;";
            String deleteShifts = "DELETE FROM Shifts;";
            String deleteWorkers = "DELETE FROM Workers;";
            String deleteRoles = "DELETE FROM Roles;";
            String deleteBranches = "DELETE FROM Branches;";

            Statement stmt = conn.createStatement();
            stmt.addBatch(deleteWorkerAvailability);
            stmt.addBatch(deleteWorkerRoles);
            stmt.addBatch(deleteShiftAvailableWorkers);
            stmt.addBatch(deleteShiftAssignedWorkers);
            stmt.addBatch(deleteShiftRoles);
            stmt.addBatch(deleteWorkerShifts);
            stmt.addBatch(deleteShifts);
            stmt.addBatch(deleteWorkers);
            stmt.addBatch(deleteRoles);
            stmt.addBatch(deleteBranches);

            stmt.executeBatch();

        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete data: " + e.getMessage(), e);
        } finally {
            closeConnection();
        }
        return true;
    }

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

    public Controller<T> setTestMode(boolean test) {
        path = test ? PATH_TEST_DEFAULT : PATH_DEFAULT;
        return this;
    }
}
