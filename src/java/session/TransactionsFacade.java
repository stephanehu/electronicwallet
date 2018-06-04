/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.AccountOwner;
import entity.Transactions;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Stephane Ehu Alias RigtheousByGod 
 */
@Stateless
public class TransactionsFacade extends AbstractFacade<Transactions> {

    @PersistenceContext(unitName = "EBankingPortalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransactionsFacade() {
        super(Transactions.class);
    }

    public List<Transactions> findByCustomerId(AccountOwner ownerId) {
        return (List<Transactions>) em.createNamedQuery("Transactions.findByCustomerId").setParameter("customersId", ownerId.getCustomers()).getResultList();
    }

    public List<Transactions> findByBusinessId(AccountOwner ownerId) {
        return (List<Transactions>) em.createNamedQuery("Transactions.findByBusinessId").setParameter("businessId", ownerId.getBusinesses()).getResultList();
    }
}
