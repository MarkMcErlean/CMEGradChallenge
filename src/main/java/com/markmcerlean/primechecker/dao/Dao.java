package com.markmcerlean.primechecker.dao;

import com.markmcerlean.primechecker.exception.FatalException;
import com.markmcerlean.primechecker.models.Model;

import java.util.List;

public interface Dao<T extends Model> {
    T read();
    void write(T input) throws FatalException;
    List<T> readAll() throws FatalException;
}
