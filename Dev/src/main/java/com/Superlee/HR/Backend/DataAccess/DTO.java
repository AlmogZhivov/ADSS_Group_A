package com.Superlee.HR.Backend.DataAccess;

import java.util.List;

public abstract class DTO {
    Controller<? extends DTO> controller;

    public boolean insert() {
        return controller.insert();
    }

    public boolean update() {
        return controller.update();
    }

    @Deprecated // Not a requirement
    public boolean delete() {
        return controller.delete();
    }

    public abstract List<? extends DTO> loadAll();
}
