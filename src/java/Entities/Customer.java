/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cian
 */
@Entity
@Table(name = "Customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
    @NamedQuery(name = "Customer.findByFName", query = "SELECT c FROM Customer c WHERE c.fName = :fName"),
    @NamedQuery(name = "Customer.findBySName", query = "SELECT c FROM Customer c WHERE c.sName = :sName"),
    @NamedQuery(name = "Customer.findByAddress", query = "SELECT c FROM Customer c WHERE c.address = :address"),
    @NamedQuery(name = "Customer.findByPhoneNo", query = "SELECT c FROM Customer c WHERE c.phoneNo = :phoneNo"),
    @NamedQuery(name = "Customer.findByPassword", query = "SELECT c FROM Customer c WHERE c.password = :password"),
    @NamedQuery(name = "Customer.findByJoinDate", query = "SELECT c FROM Customer c WHERE c.joinDate = :joinDate"),
    @NamedQuery(name = "Customer.findByIsAdmin", query = "SELECT c FROM Customer c WHERE c.isAdmin = :isAdmin"),
    @NamedQuery(name = "Customer.findByDateOfBirth", query = "SELECT c FROM Customer c WHERE c.dateOfBirth = :dateOfBirth")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Email")
    private String email;
    @Size(max = 45)
    @Column(name = "FName")
    private String fName;
    @Size(max = 45)
    @Column(name = "SName")
    private String sName;
    @Size(max = 100)
    @Column(name = "Address")
    private String address;
    @Size(max = 45)
    @Column(name = "PhoneNo")
    private String phoneNo;
    @Size(max = 45)
    @Column(name = "Password")
    private String password;
    @Column(name = "JoinDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;
    @Column(name = "IsAdmin")
    private Boolean isAdmin;
    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerEmail")
    private Collection<RentalOrder> rentalOrderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerEmail")
    private Collection<Insurance> insuranceCollection;

    public Customer() {
    }
    
    public Customer(String firstName, String lastName, String address, Date dob, String email, String phone, String password, boolean isAdmin) {
        this.fName = firstName;
        this.sName = lastName;
        this.address = address;
        this.dateOfBirth = dob;
        this.email = email;
        this.phoneNo = phone;
        this.password = password;
        this.isAdmin = isAdmin;
        
    }

    public Customer(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @XmlTransient
    public Collection<RentalOrder> getRentalOrderCollection() {
        return rentalOrderCollection;
    }

    public void setRentalOrderCollection(Collection<RentalOrder> rentalOrderCollection) {
        this.rentalOrderCollection = rentalOrderCollection;
    }

    @XmlTransient
    public Collection<Insurance> getInsuranceCollection() {
        return insuranceCollection;
    }

    public void setInsuranceCollection(Collection<Insurance> insuranceCollection) {
        this.insuranceCollection = insuranceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RentalBase.Customer[ email=" + email + " ]";
    }
    
}
