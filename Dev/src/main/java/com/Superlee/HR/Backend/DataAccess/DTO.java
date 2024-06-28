package com.Superlee.HR.Backend.DataAccess;

import java.util.List;

public abstract class DTO {
    Controller<? extends DTO> controller;
    Object[] properties;
    protected String DTOid;

    String getId() {
        return DTOid;
    }

    void setId(String id) {
        this.DTOid = id;
    }

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

    public void setProperties(Object... properties) {
        this.properties = properties;
    }

    public Object[] getProperties() {
        return properties;
    }
}
