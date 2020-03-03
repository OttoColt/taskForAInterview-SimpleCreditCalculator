package org.example.jpa.products;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DAO <T>{
    public List<T> findAll();
}
