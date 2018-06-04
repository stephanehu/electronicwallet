/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.AccountType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Stephane Ehu Alias RigtheousByGod 
 */
@Stateless
public class AccountTypeFacade extends AbstractFacade<AccountType> {

    @PersistenceContext(unitName = "EBankingPortalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountTypeFacade() {
        super(AccountType.class);
    }
    
}
