/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.AccountDocuments;
import entity.AccountOwner;
import entity.AccountType;
import entity.Accounts;
import entity.Businesses;
import entity.Customers;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
public class AccountManager {
    
    @EJB
    private BusinessesFacade businessesFacade;
    
    @EJB
    private AccountTypeFacade accountTypeFacade;
    
    @EJB
    private AccountOwnerFacade accountOwnerFacade;
    
    @EJB
    private CustomersFacade customersFacade;
    
    @PersistenceContext(unitName = "EBankingPortalPU")
    private EntityManager em;
    
    @EJB
    private AccountsFacade accountsFacade;
    
    @Resource
    SessionContext context;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int createCustomerAccount(String fName, String lName, String email, String phone, String address, String password, int acctyp, double deposit, double minimal, String type) {
        AccountOwner owner = this.createAccountOwner();
        AccountOwner ownerId = this.createCustomer(owner, fName, lName, email, phone, address, password, type);
        
        Accounts account = this.createAccount(ownerId, acctyp, deposit, minimal);
        
        return account.getId();
    }
    
    public AccountOwner createCustomer(AccountOwner owner, String fName, String lName, String email, String phone, String address, String password, String type) {
        int id = 0;
        this.createAccountDocument(owner.getId());
        if (type.equals("customer")) {
            Customers customer = new Customers();
            customer.setId(owner.getId());
            customer.setFirstname(fName);
            customer.setLastname(lName);
            customer.setEmailId(email);
            customer.setPhone(phone);
            customer.setAddress(address);
            customer.setPassword(password);
            em.persist(customer);

            id = customer.getId();
        } else if (type.equals("business")) {
            Businesses business = new Businesses();
            business.setId(owner.getId());
            business.setBusinessName(fName);
            business.setBusinessType(lName);
            business.setEmailid(email);
            business.setPhonenumber(phone);
            business.setAddress(address);
            business.setPassword(password);
            em.persist(business);
            
            id = business.getId();
        }
       
        return accountOwnerFacade.find(id);
    }
    
    public AccountOwner createAccountOwner() {
        AccountOwner owner = new AccountOwner();
        owner.setId(accountOwnerFacade.findAll().get(accountOwnerFacade.findAll().size() - 1).getId() + 1);
        owner.setNotimportant("notimportant");
        em.persist(owner);
        return owner;
    }
    
    public void createAccountDocument(int ownerId) {
        AccountDocuments accountDocuments = new AccountDocuments();
        accountDocuments.setId(ownerId);
        em.persist(accountDocuments);
        //return accountDocuments;
    }
    
    public Accounts createAccount(AccountOwner owner, int accountType, double deposit, double minimal) {
        Accounts account = new Accounts(accountsFacade.findAll().get(accountsFacade.findAll().size() - 1).getId() + 1);
        account.setDeposit(deposit);
        account.setBalance(deposit - minimal);
        account.setTypeId(accountTypeFacade.find(accountType));
        account.setOwerId(owner);        
        em.persist(account);        
        return account;
    }
    
    public Customers getCustomerDetail(int accountid) {
        int customerId = accountsFacade.find(accountid).getOwerId().getId();
        return customersFacade.find(customerId);
    }
    
    public Businesses getBusinessDetail(int accountid) {
        int businessId = accountsFacade.find(accountid).getOwerId().getId();
        return businessesFacade.find(businessId);
    }
    
    public AccountType getAccountTypeDetail(int accountid) {
        int accTypeId = accountsFacade.find(accountid).getTypeId().getId();
        return accountTypeFacade.find(accTypeId);
    }
    
    public Accounts getAccountDetail(int accountid) {
        return accountsFacade.find(accountid);
    }

    public void deposit(int accountId, double deposit) {
           Accounts account= getAccountDetail(accountId);           
           account.setBalance(account.getBalance() + deposit);
           accountsFacade.edit(account);
    }
}
