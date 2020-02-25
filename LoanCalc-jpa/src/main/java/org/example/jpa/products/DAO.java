package org.example.jpa.products;

import java.util.List;

public interface DAO <T>{
    public List<T> findAll();
}
