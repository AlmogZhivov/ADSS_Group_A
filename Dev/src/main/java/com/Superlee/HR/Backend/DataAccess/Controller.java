package com.Superlee.HR.Backend.DataAccess;

import java.util.List;

interface Controller<T> {
    boolean insert(DTO dto);

    boolean update(DTO dto);

    boolean delete(DTO dto);

    List<T> loadAll();
}
