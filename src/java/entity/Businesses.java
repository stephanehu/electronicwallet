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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Stephane Ehu Alias RigtheousByGod 
 */
@Entity
@Table(name = "businesses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Businesses.findAll", query = "SELECT b FROM Businesses b"),
    @NamedQuery(name = "Businesses.findById", query = "SELECT b FROM Businesses b WHERE b.id = :id"),
    @NamedQuery(name = "Businesses.findByBusinessName", query = "SELECT b FROM Businesses b WHERE b.businessName = :businessName"),
    @NamedQuery(name = "Businesses.findByBusinessType", query = "SELECT b FROM Businesses b WHERE b.businessType = :businessType"),
    @NamedQuery(name = "Businesses.findByEmailid", query = "SELECT b FROM Businesses b WHERE b.emailid = :emailid"),
    @NamedQuery(name = "Businesses.findByPhonenumber", query = "SELECT b FROM Businesses b WHERE b.phonenumber = :phonenumber"),
    @NamedQuery(name = "Businesses.findByAddress", query = "SELECT b FROM Businesses b WHERE b.address = :address"),
    @NamedQuery(name = "Businesses.findByPassword", query = "SELECT b FROM Businesses b WHERE b.password = :password")})
public class Businesses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 145)
    @Column(name = "business_name")
    private String businessName;
    @Size(max = 145)
    @Column(name = "business_type")
    private String businessType;
    @Size(max = 45)
    @Column(name = "emailid")
    private String emailid;
    @Size(max = 45)
    @Column(name = "phonenumber")
    private String phonenumber;
    @Size(max = 245)
    @Column(name = "address")
    private String address;
    @Size(max = 45)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessId")
    private Collection<Transactions> transactionsCollection;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private AccountOwner accountOwner;

    public Businesses() {
    }

    public Businesses(Integer id) {
        this.id = id;
    }

    public Businesses(Integer id, String businessName) {
        this.id = id;
        this.businessName = businessName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Transactions> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
    }

    public AccountOwner getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(AccountOwner accountOwner) {
        this.accountOwner = accountOwner;
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
        if (!(object instanceof Businesses)) {
            return false;
        }
        Businesses other = (Businesses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Businesses[ id=" + id + " ]";
    }
    
}
