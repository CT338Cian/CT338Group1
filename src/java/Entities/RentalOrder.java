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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cian
 */
@Entity
@Table(name = "RentalOrder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RentalOrder.findAll", query = "SELECT r FROM RentalOrder r"),
    @NamedQuery(name = "RentalOrder.findByOrderNo", query = "SELECT r FROM RentalOrder r WHERE r.orderNo = :orderNo"),
    @NamedQuery(name = "RentalOrder.findByOrderDate", query = "SELECT r FROM RentalOrder r WHERE r.orderDate = :orderDate"),
    @NamedQuery(name = "RentalOrder.findByStartDate", query = "SELECT r FROM RentalOrder r WHERE r.startDate = :startDate"),
    @NamedQuery(name = "RentalOrder.findByEndDate", query = "SELECT r FROM RentalOrder r WHERE r.endDate = :endDate")})
public class RentalOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "OrderNo")
    private Integer orderNo;
    @Column(name = "OrderDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "StartDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "EndDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderOrderNo")
    private Collection<Transaction> transactionCollection;
    @JoinColumn(name = "Customer_Email", referencedColumnName = "Email")
    @ManyToOne(optional = false)
    private Customer customerEmail;
    @JoinColumn(name = "Vehicle_Reg", referencedColumnName = "Reg")
    @ManyToOne(optional = false)
    private Vehicle vehicleReg;

    public RentalOrder() {
    }

    public RentalOrder(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @XmlTransient
    public Collection<Transaction> getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(Collection<Transaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

    public Customer getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(Customer customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleReg() {
        return vehicleReg;
    }

    public void setVehicleReg(Vehicle vehicleReg) {
        this.vehicleReg = vehicleReg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderNo != null ? orderNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RentalOrder)) {
            return false;
        }
        RentalOrder other = (RentalOrder) object;
        if ((this.orderNo == null && other.orderNo != null) || (this.orderNo != null && !this.orderNo.equals(other.orderNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RentalBase.RentalOrder[ orderNo=" + orderNo + " ]";
    }
    
}
