package org.example.ejb.DAO;

import org.example.ejb.logger.Loggable;
import org.example.jpa.products.DAO;
import org.example.jpa.products.Product;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Logger;


@Named
@Stateless
@LocalBean
public class ProductDAO implements DAO{

    @Inject
    private Logger logger;

    @PersistenceContext(unitName = "DataSourceEx")
    private EntityManager em;

    @Loggable
    public Product findById(int id) {
        String message = "findById id="+id;
        logger.info(message);
        return em.find(Product.class, id);
    }


    public List<Product> findAll() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
        Root<Product> member = criteria.from(Product.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
    }
}
