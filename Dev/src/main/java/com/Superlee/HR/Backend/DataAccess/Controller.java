package com.Superlee.HR.Backend.DataAccess;

import java.util.List;

abstract class Controller<T> {
    boolean insert(DTO dto) {
        return true;
    }

    boolean update(DTO dto) {
        String id = dto.getId();
        return true;
    }

    boolean delete(DTO dto) {
        String id = dto.getId();
        return true;
    }

    abstract List<T> loadAll();
}
