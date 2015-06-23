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
import javax.persistence.TypedQuery;
import sk.banik.registrations.entities.Unit;

/**
 *
 * @author majky
 */
@Stateless
public class UnitFacade extends AbstractFacade<Unit> {
    @PersistenceContext(unitName = "sk.banik.registrationsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UnitFacade() {
        super(Unit.class);
    }
    
    public List<Unit> findByUserId(short userId){
        TypedQuery<Unit> query = em.createNamedQuery(Unit.NAMED_QUERY_BY_USER_ID, Unit.class);
        query.setParameter("userId", userId);
        List<Unit> results = query.getResultList();
        
        return results;
    }
}
