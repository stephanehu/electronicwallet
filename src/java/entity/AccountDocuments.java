/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Stephane Ehu Alias RigtheousByGod 
 */
@Entity
@Table(name = "account_documents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountDocuments.findAll", query = "SELECT a FROM AccountDocuments a"),
    @NamedQuery(name = "AccountDocuments.findById", query = "SELECT a FROM AccountDocuments a WHERE a.id = :id"),
    @NamedQuery(name = "AccountDocuments.findByIdentityProof", query = "SELECT a FROM AccountDocuments a WHERE a.identityProof = :identityProof"),
    @NamedQuery(name = "AccountDocuments.findByAddressProof", query = "SELECT a FROM AccountDocuments a WHERE a.addressProof = :addressProof"),
    @NamedQuery(name = "AccountDocuments.findByLegalStatus", query = "SELECT a FROM AccountDocuments a WHERE a.legalStatus = :legalStatus"),
    @NamedQuery(name = "AccountDocuments.findByPhotoProfile", query = "SELECT a FROM AccountDocuments a WHERE a.photoProfile = :photoProfile")})
public class AccountDocuments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 300)
    @Column(name = "identity_proof")
    private String identityProof;
    @Size(max = 300)
    @Column(name = "address_proof")
    private String addressProof;
    @Size(max = 300)
    @Column(name = "legal_status")
    private String legalStatus;
    @Size(max = 300)
    @Column(name = "photo_profile")
    private String photoProfile;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private AccountOwner accountOwner;

    public AccountDocuments() {
    }

    public AccountDocuments(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentityProof() {
        return identityProof;
    }

    public void setIdentityProof(String identityProof) {
        this.identityProof = identityProof;
    }

    public String getAddressProof() {
        return addressProof;
    }

    public void setAddressProof(String addressProof) {
        this.addressProof = addressProof;
    }

    public String getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(String legalStatus) {
        this.legalStatus = legalStatus;
    }

    public String getPhotoProfile() {
        return photoProfile;
    }

    public void setPhotoProfile(String photoProfile) {
        this.photoProfile = photoProfile;
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
        if (!(object instanceof AccountDocuments)) {
            return false;
        }
        AccountDocuments other = (AccountDocuments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AccountDocuments[ id=" + id + " ]";
    }
    
}
