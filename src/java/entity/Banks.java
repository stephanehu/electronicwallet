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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Stephane
 */
@Entity
@Table(name = "banks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banks.findAll", query = "SELECT b FROM Banks b"),
    @NamedQuery(name = "Banks.findById", query = "SELECT b FROM Banks b WHERE b.id = :id"),
    @NamedQuery(name = "Banks.findByBankName", query = "SELECT b FROM Banks b WHERE b.bankName = :bankName"),
    @NamedQuery(name = "Banks.findByBankAddress", query = "SELECT b FROM Banks b WHERE b.bankAddress = :bankAddress"),
    @NamedQuery(name = "Banks.findByBankLandline", query = "SELECT b FROM Banks b WHERE b.bankLandline = :bankLandline")})
public class Banks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 145)
    @Column(name = "bank_name")
    private String bankName;
    @Size(max = 245)
    @Column(name = "bank_address")
    private String bankAddress;
    @Size(max = 20)
    @Column(name = "bank_landline")
    private String bankLandline;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bankId")
    private Collection<Services> servicesCollection;

    public Banks() {
    }

    public Banks(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankLandline() {
        return bankLandline;
    }

    public void setBankLandline(String bankLandline) {
        this.bankLandline = bankLandline;
    }

    @XmlTransient
    public Collection<Services> getServicesCollection() {
        return servicesCollection;
    }

    public void setServicesCollection(Collection<Services> servicesCollection) {
        this.servicesCollection = servicesCollection;
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
        if (!(object instanceof Banks)) {
            return false;
        }
        Banks other = (Banks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Banks[ id=" + id + " ]";
    }
    
}
