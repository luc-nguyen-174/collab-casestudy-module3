package com.case_study.casemd3.service.address;

import com.case_study.casemd3.model.Address;
import com.case_study.casemd3.service.IGeneric;

import java.util.List;

public interface IAddress extends IGeneric<Address> {
    @Override
    List<Address> findAll();

    @Override
    void save(Address generic);

    @Override
    Address findById(int id);

    @Override
    boolean update(int id, Address generic);

    @Override
    boolean remove(int id);
}
