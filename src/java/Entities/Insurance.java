/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cian
 */
@Entity
@Table(name = "Insurance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insurance.findAll", query = "SELECT i FROM Insurance i"),
    @NamedQuery(name = "Insurance.findByInsuranceNo", query = "SELECT i FROM Insurance i WHERE i.insuranceNo = :insuranceNo"),
    @NamedQuery(name = "Insurance.findByProvider", query = "SELECT i FROM Insurance i WHERE i.provider = :provider"),
    @NamedQuery(name = "Insurance.findByCoverType", query = "SELECT i FROM Insurance i WHERE i.coverType = :coverType")})
public class Insurance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "InsuranceNo")
    private Integer insuranceNo;
    @Size(max = 45)
    @Column(name = "Provider")
    private String provider;
    @Size(max = 45)
    @Column(name = "CoverType")
    private String coverType;
    @JoinColumn(name = "Customer_Email", referencedColumnName = "Email")
    @ManyToOne(optional = false)
    private Customer customerEmail;

    public Insurance() {
    }

    public Insurance(Integer insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    public Integer getInsuranceNo() {
        return insuranceNo;
    }

    public void setInsuranceNo(Integer insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public Customer getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(Customer customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insuranceNo != null ? insuranceNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insurance)) {
            return false;
        }
        Insurance other = (Insurance) object;
        if ((this.insuranceNo == null && other.insuranceNo != null) || (this.insuranceNo != null && !this.insuranceNo.equals(other.insuranceNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RentalBase.Insurance[ insuranceNo=" + insuranceNo + " ]";
    }
    
}
