package com.Superlee.HR.Backend.DataAccess;

public class DataAccessException extends RuntimeException {
    public DataAccessException(Exception e) {
        super(e);
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Exception e) {
        super(message, e);
    }

    public DataAccessException() {
        super();
    }
}
