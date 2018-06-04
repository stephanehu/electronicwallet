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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "account_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountType.findAll", query = "SELECT a FROM AccountType a"),
    @NamedQuery(name = "AccountType.findById", query = "SELECT a FROM AccountType a WHERE a.id = :id"),
    @NamedQuery(name = "AccountType.findByName", query = "SELECT a FROM AccountType a WHERE a.name = :name"),
    @NamedQuery(name = "AccountType.findByMinimumDeporit", query = "SELECT a FROM AccountType a WHERE a.minimumDeporit = :minimumDeporit")})
public class AccountType implements Serializable {

    @Column(name = "discount")
    private double discount;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 145)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "minimum_deporit")
    private double minimumDeporit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
    private Collection<Accounts> accountsCollection;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Services serviceId;

    public AccountType() {
    }

    public AccountType(Integer id) {
        this.id = id;
    }

    public AccountType(Integer id, String name, double minimumDeporit) {
        this.id = id;
        this.name = name;
        this.minimumDeporit = minimumDeporit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinimumDeporit() {
        return minimumDeporit;
    }

    public void setMinimumDeporit(double minimumDeporit) {
        this.minimumDeporit = minimumDeporit;
    }

    @XmlTransient
    public Collection<Accounts> getAccountsCollection() {
        return accountsCollection;
    }

    public void setAccountsCollection(Collection<Accounts> accountsCollection) {
        this.accountsCollection = accountsCollection;
    }

    public Services getServiceId() {
        return serviceId;
    }

    public void setServiceId(Services serviceId) {
        this.serviceId = serviceId;
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
        if (!(object instanceof AccountType)) {
            return false;
        }
        AccountType other = (AccountType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AccountType[ id=" + id + " ]";
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
}
