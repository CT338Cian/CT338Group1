/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Cian
 */
@Entity
@Table(name = "Vehicle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehicle.findAll", query = "SELECT v FROM Vehicle v"),
    @NamedQuery(name = "Vehicle.findByReg", query = "SELECT v FROM Vehicle v WHERE v.reg = :reg"),
    @NamedQuery(name = "Vehicle.findByMake", query = "SELECT v FROM Vehicle v WHERE v.make = :make"),
    @NamedQuery(name = "Vehicle.findByModel", query = "SELECT v FROM Vehicle v WHERE v.model = :model"),
    @NamedQuery(name = "Vehicle.findByYear", query = "SELECT v FROM Vehicle v WHERE v.year = :year"),
    @NamedQuery(name = "Vehicle.findByColour", query = "SELECT v FROM Vehicle v WHERE v.colour = :colour"),
    @NamedQuery(name = "Vehicle.findByEngineCC", query = "SELECT v FROM Vehicle v WHERE v.engineCC = :engineCC"),
    @NamedQuery(name = "Vehicle.findByPrice", query = "SELECT v FROM Vehicle v WHERE v.price = :price"),
    @NamedQuery(name = "Vehicle.findByFuelType", query = "SELECT v FROM Vehicle v WHERE v.fuelType = :fuelType"),
    @NamedQuery(name = "Vehicle.findByTransmission", query = "SELECT v FROM Vehicle v WHERE v.transmission = :transmission"),
    @NamedQuery(name = "Vehicle.findByIsAvailable", query = "SELECT v FROM Vehicle v WHERE v.isAvailable = :isAvailable")})
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Reg")
    private String reg;
    @Size(max = 45)
    @Column(name = "Make")
    private String make;
    @Size(max = 45)
    @Column(name = "Model")
    private String model;
    @Column(name = "Year")
    private Integer year;
    @Size(max = 45)
    @Column(name = "Colour")
    private String colour;
    @Size(max = 45)
    @Column(name = "EngineCC")
    private String engineCC;
    @Column(name = "Price")
    private Integer price;
    @Size(max = 45)
    @Column(name = "FuelType")
    private String fuelType;
    @Size(max = 45)
    @Column(name = "Transmission")
    private String transmission;
    @Column(name = "IsAvailable")
    private Boolean isAvailable;
    @Column(name = "ImagePath")
    private String imagePath;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleReg")
    private Collection<RentalOrder> rentalOrderCollection;

    public Vehicle() {
    }

    public Vehicle(String reg) {
        this.reg = reg;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getEngineCC() {
        return engineCC;
    }

    public void setEngineCC(String engineCC) {
        this.engineCC = engineCC;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    public String getImagePath() {
        return imagePath;
    }

    @XmlTransient
    public Collection<RentalOrder> getRentalOrderCollection() {
        return rentalOrderCollection;
    }

    public void setRentalOrderCollection(Collection<RentalOrder> rentalOrderCollection) {
        this.rentalOrderCollection = rentalOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reg != null ? reg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) object;
        if ((this.reg == null && other.reg != null) || (this.reg != null && !this.reg.equals(other.reg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RentalBase.Vehicle[ reg=" + reg + " ]";
    }
    
}
