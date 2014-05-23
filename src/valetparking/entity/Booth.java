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
@Table(name = "booth", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Booth.findAll", query = "SELECT b FROM Booth b"),
    @NamedQuery(name = "Booth.findByBoothid", query = "SELECT b FROM Booth b WHERE b.boothid = :boothid"),
    @NamedQuery(name = "Booth.findByBoothname", query = "SELECT b FROM Booth b WHERE b.boothname = :boothname"),
    @NamedQuery(name = "Booth.findByDriver", query = "SELECT b FROM Booth b WHERE b.driver = :driver"),
    @NamedQuery(name = "Booth.findByPrintin", query = "SELECT b FROM Booth b WHERE b.printin = :printin"),
    @NamedQuery(name = "Booth.findByPrintout", query = "SELECT b FROM Booth b WHERE b.printout = :printout"),
    @NamedQuery(name = "Booth.findByPrinterport", query = "SELECT b FROM Booth b WHERE b.printerport = :printerport"),
    @NamedQuery(name = "Booth.findByStatus", query = "SELECT b FROM Booth b WHERE b.status = :status"),
    @NamedQuery(name = "Booth.findByCreateddate", query = "SELECT b FROM Booth b WHERE b.createddate = :createddate"),
    @NamedQuery(name = "Booth.findByCreatedby", query = "SELECT b FROM Booth b WHERE b.createdby = :createdby"),
    @NamedQuery(name = "Booth.findByUpdateddate", query = "SELECT b FROM Booth b WHERE b.updateddate = :updateddate"),
    @NamedQuery(name = "Booth.findByUpdatedby", query = "SELECT b FROM Booth b WHERE b.updatedby = :updatedby")})
public class Booth implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "boothid")
    private String boothid;
    @Basic(optional = false)
    @Column(name = "boothname")
    private String boothname;
    @Basic(optional = false)
    @Column(name = "driver")
    private boolean driver;
    @Basic(optional = false)
    @Column(name = "printin")
    private boolean printin;
    @Basic(optional = false)
    @Column(name = "printout")
    private boolean printout;
    @Basic(optional = false)
    @Column(name = "printerport")
    private String printerport;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Basic(optional = false)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "updateddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateddate;
    @Column(name = "updatedby")
    private String updatedby;

    public Booth() {
    }

    public Booth(String boothid) {
        this.boothid = boothid;
    }

    public Booth(String boothid, String boothname, boolean driver, boolean printin, boolean printout, String printerport, boolean status, Date createddate, String createdby) {
        this.boothid = boothid;
        this.boothname = boothname;
        this.driver = driver;
        this.printin = printin;
        this.printout = printout;
        this.printerport = printerport;
        this.status = status;
        this.createddate = createddate;
        this.createdby = createdby;
    }

    public String getBoothid() {
        return boothid;
    }

    public void setBoothid(String boothid) {
        this.boothid = boothid;
    }

    public String getBoothname() {
        return boothname;
    }

    public void setBoothname(String boothname) {
        this.boothname = boothname;
    }

    public boolean getDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    public boolean getPrintin() {
        return printin;
    }

    public void setPrintin(boolean printin) {
        this.printin = printin;
    }

    public boolean getPrintout() {
        return printout;
    }

    public void setPrintout(boolean printout) {
        this.printout = printout;
    }

    public String getPrinterport() {
        return printerport;
    }

    public void setPrinterport(String printerport) {
        this.printerport = printerport;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(Date updateddate) {
        this.updateddate = updateddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (boothid != null ? boothid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booth)) {
            return false;
        }
        Booth other = (Booth) object;
        if ((this.boothid == null && other.boothid != null) || (this.boothid != null && !this.boothid.equals(other.boothid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Booth[ boothid=" + boothid + " ]";
    }
    
}
