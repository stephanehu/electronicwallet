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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Stephane
 */
@Entity
@Table(name = "services")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Services.findAll", query = "SELECT s FROM Services s"),
    @NamedQuery(name = "Services.findById", query = "SELECT s FROM Services s WHERE s.id = :id"),
    @NamedQuery(name = "Services.findByServiceName", query = "SELECT s FROM Services s WHERE s.serviceName = :serviceName"),
    @NamedQuery(name = "Services.findByERecharge", query = "SELECT s FROM Services s WHERE s.eRecharge = :eRecharge"),
    @NamedQuery(name = "Services.findByEWaterbilling", query = "SELECT s FROM Services s WHERE s.eWaterbilling = :eWaterbilling"),
    @NamedQuery(name = "Services.findByEElectricitybilling", query = "SELECT s FROM Services s WHERE s.eElectricitybilling = :eElectricitybilling")})
public class Services implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 145)
    @Column(name = "service_name")
    private String serviceName;
    @Lob
    @Size(max = 65535)
    @Column(name = "service_description")
    private String serviceDescription;
    @Column(name = "e_recharge")
    private Boolean eRecharge;
    @Column(name = "e_waterbilling")
    private Boolean eWaterbilling;
    @Column(name = "e_electricitybilling")
    private Boolean eElectricitybilling;
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Banks bankId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceId")
    private Collection<AccountType> accountTypeCollection;

    public Services() {
    }

    public Services(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Boolean getERecharge() {
        return eRecharge;
    }

    public void setERecharge(Boolean eRecharge) {
        this.eRecharge = eRecharge;
    }

    public Boolean getEWaterbilling() {
        return eWaterbilling;
    }

    public void setEWaterbilling(Boolean eWaterbilling) {
        this.eWaterbilling = eWaterbilling;
    }

    public Boolean getEElectricitybilling() {
        return eElectricitybilling;
    }

    public void setEElectricitybilling(Boolean eElectricitybilling) {
        this.eElectricitybilling = eElectricitybilling;
    }

    public Banks getBankId() {
        return bankId;
    }

    public void setBankId(Banks bankId) {
        this.bankId = bankId;
    }

    @XmlTransient
    public Collection<AccountType> getAccountTypeCollection() {
        return accountTypeCollection;
    }

    public void setAccountTypeCollection(Collection<AccountType> accountTypeCollection) {
        this.accountTypeCollection = accountTypeCollection;
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
        if (!(object instanceof Services)) {
            return false;
        }
        Services other = (Services) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Services[ id=" + id + " ]";
    }
    
}
