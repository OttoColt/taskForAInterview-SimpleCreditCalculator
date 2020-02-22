package org.example.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@Named
public class UserDao {
    @PersistenceContext(unitName = "DataSourceEx")
    private EntityManager em;

    private User newMember;

    public void save(){
        System.out.println(newMember);
        em.persist(newMember);
        initNewMember();
    }

    public void test(){
        System.out.println(findAll());
    }

    public User findByEmail(String login) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> member = criteria.from(User.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(member).where(cb.equal(member.get("login"), login));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<User> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> member = criteria.from(User.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
    }

    @Produces
    @Named
    public User getNewMember() {
        return newMember;
    }


    @PostConstruct
    public void initNewMember() {
        newMember = new User();
    }
}