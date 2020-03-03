package org.example.ejb.DAO;

import org.example.jpa.users.User;

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

    private User member;

    public List<User> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> member = criteria.from(User.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
    }

    public String isRegistered() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> member = criteria.from(User.class);

        criteria.select(member).where(cb.equal(member.get("login"), this.member.getLogin()));

        User user = em.createQuery(criteria).getSingleResult();


        if (user.getPassword().equals(this.member.getPassword())) {
            return "true";
        }
        return null;
    }

    @Produces
    @Named
    public User getMember() {
        return member;
    }


    @PostConstruct
    public void initNewMember() {
        member = new User();
    }
}