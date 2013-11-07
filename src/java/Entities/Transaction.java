/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cian
 */
@Entity
@Table(name = "Transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findByTransactionID", query = "SELECT t FROM Transaction t WHERE t.transactionID = :transactionID"),
    @NamedQuery(name = "Transaction.findByAmount", query = "SELECT t FROM Transaction t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transaction.findByCardType", query = "SELECT t FROM Transaction t WHERE t.cardType = :cardType"),
    @NamedQuery(name = "Transaction.findByCardNo", query = "SELECT t FROM Transaction t WHERE t.cardNo = :cardNo")})
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TransactionID")
    private Integer transactionID;
    @Column(name = "Amount")
    private Integer amount;
    @Size(max = 45)
    @Column(name = "CardType")
    private String cardType;
    @Column(name = "CardNo")
    private Integer cardNo;
    @JoinColumn(name = "Order_OrderNo", referencedColumnName = "OrderNo")
    @ManyToOne(optional = false)
    private RentalOrder orderOrderNo;

    public Transaction() {
    }

    public Transaction(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public RentalOrder getOrderOrderNo() {
        return orderOrderNo;
    }

    public void setOrderOrderNo(RentalOrder orderOrderNo) {
        this.orderOrderNo = orderOrderNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionID != null ? transactionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.transactionID == null && other.transactionID != null) || (this.transactionID != null && !this.transactionID.equals(other.transactionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RentalBase.Transaction[ transactionID=" + transactionID + " ]";
    }
    
}
