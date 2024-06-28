package com.Superlee.HR.Backend.DataAccess;

import java.util.List;

public abstract class DTO {
    Controller<? extends DTO> controller;

    public boolean insert() {
        return controller.insert(this);
    }

    public boolean update() {
        return controller.update(this);
    }

    public boolean delete() {
        return controller.delete(this);
    }

    public abstract List<? extends DTO> loadAll();
}
