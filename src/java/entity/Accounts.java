/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Stephane Ehu Alias RigtheousByGod 
 */
@Entity
@Table(name = "accounts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a"),
    @NamedQuery(name = "Accounts.findById", query = "SELECT a FROM Accounts a WHERE a.id = :id"),
    @NamedQuery(name = "Accounts.findByDeposit", query = "SELECT a FROM Accounts a WHERE a.deposit = :deposit"),
    @NamedQuery(name = "Accounts.findByOwnerId", query = "SELECT a FROM Accounts a WHERE a.owerId = :owerId"),
    @NamedQuery(name = "Accounts.findByBalance", query = "SELECT a FROM Accounts a WHERE a.balance = :balance")})
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "deposit")
    private double deposit;
    @Column(name = "balance")
    private double balance;
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AccountType typeId;
    @JoinColumn(name = "ower_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AccountOwner owerId;

    public Accounts() {
    }

    public Accounts(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getTypeId() {
        return typeId;
    }

    public void setTypeId(AccountType typeId) {
        this.typeId = typeId;
    }

    public AccountOwner getOwerId() {
        return owerId;
    }

    public void setOwerId(AccountOwner owerId) {
        this.owerId = owerId;
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
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Accounts[ id=" + id + " ]";
    }

}
