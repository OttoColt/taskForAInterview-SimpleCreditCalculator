package org.example.jpa.products;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.List;

@Local
public interface DAO <T>{
    public Product findById(int id);
    public List<T> findAll();
}