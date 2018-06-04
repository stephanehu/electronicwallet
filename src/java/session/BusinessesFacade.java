/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Businesses;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Stephane
 */
@Stateless
public class BusinessesFacade extends AbstractFacade<Businesses> {

    @PersistenceContext(unitName = "EBankingPortalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BusinessesFacade() {
        super(Businesses.class);
    }

    public Collection<Businesses> findByBusinessType(String businessType) {
       return (Collection<Businesses>) em.createNamedQuery("Businesses.findByBusinessType").setParameter("businessType", businessType).getResultList();
    }
    
}
