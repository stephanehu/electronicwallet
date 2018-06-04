/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.AccountOwner;
import entity.Accounts;
import entity.Businesses;
import entity.Customers;
import entity.Transactions;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Stephane Ehu Alias RigtheousByGod 
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TransactionManager {

    @EJB
    private AccountOwnerFacade accountOwnerFacade;

    @EJB
    private TransactionsFacade transactionsFacade;

    @EJB
    private AccountsFacade accountsFacade;

    @EJB
    private CustomersFacade customersFacade;
    @PersistenceContext(unitName = "EBankingPortalPU")
    private EntityManager em;

    @EJB
    private BusinessesFacade businessesFacade;

    public int transact(String number, String amount, String customerId, String businessId) {
        Transactions transaction = null;
        try {
            Customers customers = customersFacade.find(Integer.parseInt(customerId));
            Businesses businesses = businessesFacade.find(Integer.parseInt(businessId));
            double montant = Double.parseDouble(amount);
            AccountOwner ownerId = customers.getAccountOwner();
            if (ownerId == null) {
                ownerId = accountOwnerFacade.findAll().get(accountOwnerFacade.findAll().size() - 1);
            }
            Accounts customerAccount = accountsFacade.findByOwnerId(ownerId);
            System.out.println("cust account cust : "+customers);
            Accounts businessAccount = accountsFacade.findByOwnerId(businesses.getAccountOwner());

            if (customerAccount.getBalance() < montant) {
                return -1;
            }
            transaction = new Transactions();
            transaction.setId(gettransactionId());
            transaction.setAmount(montant);

            transaction.setBusinessId(businessAccount.getOwerId().getBusinesses());
            double bonus = montant + (montant * (businessAccount.getTypeId().getDiscount() / 100));
            System.out.println("bonus : " + bonus);
            businessAccount.setBalance(businessAccount.getBalance() + bonus);

            transaction.setCustomersId(customers);
            double charge = montant - (montant * (customerAccount.getTypeId().getDiscount() / 100));
            System.out.println("charge : " + charge);
            customerAccount.setBalance(customerAccount.getBalance() - charge);

            transaction.setDateTransacted(new Date());
            transaction.setDetail(number);
            em.persist(transaction);
        } catch (Exception e) {
            transaction = new Transactions(-1);
        }

        return transaction.getId();
    }

    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void persist(Object object) {
        em.persist(object);
    }

    int gettransactionId() {
        List<Transactions> transactionList = transactionsFacade.findAll();
        int id = 0;
        if (transactionList.size() <= 0) {
            id = 1;
        } else {
            id = transactionList.get(transactionList.size() - 1).getId() + 1;
        }
        return id;
    }
}
