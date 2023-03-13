package com.case_study.casemd3.service;

import java.util.List;

public interface IGeneric<T> {
    List<T> findAll();

    void save(T generic);

    T findById(int id);

    boolean update(int id, T generic);

    boolean remove(int id);
}
