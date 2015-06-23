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
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.Metamodel;
import sk.banik.registrations.entities.Registration;
import sk.banik.registrations.entities.Registration_;
import sk.banik.registrations.entities.Unit;
import sk.banik.registrations.entities.Unit_;

/**
 *
 * @author majky
 */
@Stateless
public class RegistrationFacade extends AbstractFacade<Registration> {
    @PersistenceContext(unitName = "sk.banik.registrationsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistrationFacade() {
        super(Registration.class);
    }
    
    public List<Registration> findAllByAgent(Short agentId){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Registration> criteriaQuery = cb.createQuery(Registration.class);
        
        Root<Registration> registrationRoot = criteriaQuery.from(Registration.class);
        registrationRoot.join(Registration_.unit);
        criteriaQuery.select(registrationRoot);
        criteriaQuery.where(cb.equal(registrationRoot
                .get("unit")
                .get("user")
                .get("id"), agentId));
        
        List<Registration> result = em.createQuery(criteriaQuery).getResultList();
        
        return result;
    }
    
    public List<Registration> findAllByManager(Short managerId){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Registration> criteriaQuery = cb.createQuery(Registration.class);
        
        Root<Registration> registrationRoot = criteriaQuery.from(Registration.class);
        registrationRoot.join(Registration_.unit).join(Unit_.branch);
        criteriaQuery.select(registrationRoot);
        criteriaQuery.where(cb.equal(registrationRoot
                .get("unit")
                .get("branch")
                .get("managerId")
                .get("id"), managerId));
        
        List<Registration> result = em.createQuery(criteriaQuery).getResultList();
        
        return result;
    }
}
