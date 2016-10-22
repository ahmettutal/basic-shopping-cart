package com.springmvc.bcart.services;

import java.util.List;

public interface Strategy {

    List<Object> findAll();

    Object save(Object object);

    void deleteByID(String Id);

    Object findById(String Id);

    List<Object> findListById(String Id);
}
