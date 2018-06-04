/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Stephane Ehu Alias RigtheousByGod 
 */
@Entity
@Table(name = "transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t"),
    @NamedQuery(name = "Transactions.findById", query = "SELECT t FROM Transactions t WHERE t.id = :id"),
     @NamedQuery(name = "Transactions.findByCustomerId", query = "SELECT t FROM Transactions t WHERE t.customersId = :customersId"),
     @NamedQuery(name = "Transactions.findByBusinessId", query = "SELECT t FROM Transactions t WHERE t.businessId = :businessId"),
    @NamedQuery(name = "Transactions.findByAmount", query = "SELECT t FROM Transactions t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transactions.findByDateTransacted", query = "SELECT t FROM Transactions t WHERE t.dateTransacted = :dateTransacted")})
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "date_transacted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTransacted;
    @Lob
    @Size(max = 65535)
    @Column(name = "detail")
    private String detail;
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customers customersId;
    @JoinColumn(name = "business_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Businesses businessId;

    public Transactions() {
    }

    public Transactions(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDateTransacted() {
        return dateTransacted;
    }

    public void setDateTransacted(Date dateTransacted) {
        this.dateTransacted = dateTransacted;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Customers getCustomersId() {
        return customersId;
    }

    public void setCustomersId(Customers customersId) {
        this.customersId = customersId;
    }

    public Businesses getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Businesses businessId) {
        this.businessId = businessId;
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
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Transactions[ id=" + id + " ]";
    }
    
}
