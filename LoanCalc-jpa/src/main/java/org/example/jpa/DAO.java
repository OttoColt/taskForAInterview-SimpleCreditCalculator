package org.example.jpa;

import java.util.List;

public interface DAO <T>{
    public List<T> findAll();
}
