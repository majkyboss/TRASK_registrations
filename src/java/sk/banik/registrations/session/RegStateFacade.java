/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.banik.registrations.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sk.banik.registrations.entities.RegState;

/**
 *
 * @author majky
 */
@Stateless
public class RegStateFacade extends AbstractFacade<RegState> {
    @PersistenceContext(unitName = "sk.banik.registrationsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegStateFacade() {
        super(RegState.class);
    }
    
}
