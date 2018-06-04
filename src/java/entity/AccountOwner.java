/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Stephane Ehu Alias RigtheousByGod 
 */
@Entity
@Table(name = "account_owner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountOwner.findAll", query = "SELECT a FROM AccountOwner a"),
    @NamedQuery(name = "AccountOwner.findById", query = "SELECT a FROM AccountOwner a WHERE a.id = :id")})
public class AccountOwner implements Serializable {

    @Size(max = 45)
    @Column(name = "notimportant")
    private String notimportant;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owerId")
    private Collection<Accounts> accountsCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "accountOwner")
    private Customers customers;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "accountOwner")
    private AccountDocuments accountDocuments;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "accountOwner")
    private Businesses businesses;

    public AccountOwner() {
    }

    public AccountOwner(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<Accounts> getAccountsCollection() {
        return accountsCollection;
    }

    public void setAccountsCollection(Collection<Accounts> accountsCollection) {
        this.accountsCollection = accountsCollection;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public AccountDocuments getAccountDocuments() {
        return accountDocuments;
    }

    public void setAccountDocuments(AccountDocuments accountDocuments) {
        this.accountDocuments = accountDocuments;
    }

    public Businesses getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Businesses businesses) {
        this.businesses = businesses;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountOwner)) {
            return false;
        }
        AccountOwner other = (AccountOwner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AccountOwner[ id=" + id + " ]";
    }

    public String getNotimportant() {
        return notimportant;
    }

    public void setNotimportant(String notimportant) {
        this.notimportant = notimportant;
    }
    
}
