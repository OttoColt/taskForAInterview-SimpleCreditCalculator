package org.example.ejb;

import org.example.jpa.Product;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Stateless
@Named
public class ProductDAO {

    @PersistenceContext(unitName = "DataSourceEx")
    private EntityManager em;

    public void test(){
        System.out.println(findAll());
    }

    public List<Product> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
        Root<Product> member = criteria.from(Product.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
    }
}