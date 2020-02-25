package org.example.jpa.products;

import java.util.List;


//TODO figure out how to use the interface in the beans
public interface DAO <T>{
    public List<T> findAll();
}
