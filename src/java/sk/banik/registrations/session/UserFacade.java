/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.banik.registrations.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sk.banik.registrations.entities.Branch;
import sk.banik.registrations.entities.Branch_;
import sk.banik.registrations.entities.Unit_;
import sk.banik.registrations.entities.User;
import sk.banik.registrations.entities.User_;

/**
 *
 * @author majky
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "sk.banik.registrationsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public List<User> findAllByManager(short managerId) {
                
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        
        Root<User> userRoot = criteriaQuery.from(User.class);
        userRoot.join(User_.unitCollection)
                .join(Unit_.branch);
        
        criteriaQuery.where(cb.equal(userRoot
                .get("unitCollection")
                .get("branch")
                .get("managerId")
                .get("id"), managerId));
        
        List<User> result = em.createQuery(criteriaQuery).getResultList();
        
        return result;
    }
}
