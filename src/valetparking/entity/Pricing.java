/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "pricing", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pricing.findAll", query = "SELECT p FROM Pricing p"),
    @NamedQuery(name = "Pricing.findByPricingid", query = "SELECT p FROM Pricing p WHERE p.pricingid = :pricingid"),
    @NamedQuery(name = "Pricing.findByPricingname", query = "SELECT p FROM Pricing p WHERE p.pricingname = :pricingname"),
    @NamedQuery(name = "Pricing.findByPricinghourly", query = "SELECT p FROM Pricing p WHERE p.pricinghourly = :pricinghourly"),
    @NamedQuery(name = "Pricing.findByPricingservices", query = "SELECT p FROM Pricing p WHERE p.pricingservices = :pricingservices"),
    @NamedQuery(name = "Pricing.findByPricingenable", query = "SELECT p FROM Pricing p WHERE p.pricingenable = :pricingenable"),
    @NamedQuery(name = "Pricing.findByPricingservicesenable", query = "SELECT p FROM Pricing p WHERE p.pricingservicesenable = :pricingservicesenable"),
    @NamedQuery(name = "Pricing.findByLimittimeenable", query = "SELECT p FROM Pricing p WHERE p.limittimeenable = :limittimeenable"),
    @NamedQuery(name = "Pricing.findByLimittimecharge", query = "SELECT p FROM Pricing p WHERE p.limittimecharge = :limittimecharge"),
    @NamedQuery(name = "Pricing.findByLimitime", query = "SELECT p FROM Pricing p WHERE p.limitime = :limitime"),
    @NamedQuery(name = "Pricing.findByNextday", query = "SELECT p FROM Pricing p WHERE p.nextday = :nextday")})
public class Pricing implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pricingid")
    private Integer pricingid;
    @Basic(optional = false)
    @Column(name = "pricingname")
    private String pricingname;
    @Basic(optional = false)
    @Column(name = "pricinghourly")
    private long pricinghourly;
    @Basic(optional = false)
    @Column(name = "pricingservices")
    private long pricingservices;
    @Basic(optional = false)
    @Column(name = "pricingenable")
    private boolean pricingenable;
    @Basic(optional = false)
    @Column(name = "pricingservicesenable")
    private boolean pricingservicesenable;
    @Basic(optional = false)
    @Column(name = "missingticketenable")
    private boolean missingticketenable;
    @Basic(optional = false)
    @Column(name = "missingticketcharge")
    private double missingticketcharge;    
    @Basic(optional = false)
    @Column(name = "limittimeenable")
    private boolean limittimeenable;
    @Basic(optional = false)
    @Column(name = "limittimecharge")
    private double limittimecharge;
    @Basic(optional = false)
    @Column(name = "limitime")
    @Temporal(TemporalType.TIME)
    private Date limitime;
    @Basic(optional = false)
    @Column(name = "nextday")
    private boolean nextday;

    public Pricing() {
    }

    public Pricing(Integer pricingid) {
        this.pricingid = pricingid;
    }

    public Pricing(Integer pricingid, String pricingname, long pricinghourly, long pricingservices, boolean pricingenable, boolean pricingservicesenable, boolean limittimeenable, double limittimecharge, Date limitime, boolean nextday) {
        this.pricingid = pricingid;
        this.pricingname = pricingname;
        this.pricinghourly = pricinghourly;
        this.pricingservices = pricingservices;
        this.pricingenable = pricingenable;
        this.pricingservicesenable = pricingservicesenable;
        this.limittimeenable = limittimeenable;
        this.limittimecharge = limittimecharge;
        this.limitime = limitime;
        this.nextday = nextday;
    }

    public Integer getPricingid() {
        return pricingid;
    }

    public void setPricingid(Integer pricingid) {
        this.pricingid = pricingid;
    }

    public String getPricingname() {
        return pricingname;
    }

    public void setPricingname(String pricingname) {
        this.pricingname = pricingname;
    }

    public long getPricinghourly() {
        return pricinghourly;
    }

    public void setPricinghourly(long pricinghourly) {
        this.pricinghourly = pricinghourly;
    }

    public long getPricingservices() {
        return pricingservices;
    }

    public void setPricingservices(long pricingservices) {
        this.pricingservices = pricingservices;
    }

    public boolean getPricingenable() {
        return pricingenable;
    }

    public void setPricingenable(boolean pricingenable) {
        this.pricingenable = pricingenable;
    }

    public boolean getPricingservicesenable() {
        return pricingservicesenable;
    }

    public void setPricingservicesenable(boolean pricingservicesenable) {
        this.pricingservicesenable = pricingservicesenable;
    }

    public boolean getMissingticketenable() {
        return missingticketenable;
    }

    public void setMissingticketenable(boolean missingticketenable) {
        this.missingticketenable = missingticketenable;
    }

    public double getMissingticketcharge() {
        return missingticketcharge;
    }

    public void setMissingticketcharge(double missingticketcharge) {
        this.missingticketcharge = missingticketcharge;
    }

    
    public boolean getLimittimeenable() {
        return limittimeenable;
    }

    public void setLimittimeenable(boolean limittimeenable) {
        this.limittimeenable = limittimeenable;
    }

    public double getLimittimecharge() {
        return limittimecharge;
    }

    public void setLimittimecharge(double limittimecharge) {
        this.limittimecharge = limittimecharge;
    }

    public Date getLimitime() {
        return limitime;
    }

    public void setLimitime(Date limitime) {
        this.limitime = limitime;
    }

    public boolean getNextday() {
        return nextday;
    }

    public void setNextday(boolean nextday) {
        this.nextday = nextday;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pricingid != null ? pricingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pricing)) {
            return false;
        }
        Pricing other = (Pricing) object;
        if ((this.pricingid == null && other.pricingid != null) || (this.pricingid != null && !this.pricingid.equals(other.pricingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Pricing[ pricingid=" + pricingid + " ]";
    }
    
}
