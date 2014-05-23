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
 * @author wahhid
 */
@Entity
@Table(name = "voucher", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voucher.findAll", query = "SELECT v FROM Voucher v"),
    @NamedQuery(name = "Voucher.findByVoucherid", query = "SELECT v FROM Voucher v WHERE v.voucherid = :voucherid"),
    @NamedQuery(name = "Voucher.findByVoucherdesc", query = "SELECT v FROM Voucher v WHERE v.voucherdesc = :voucherdesc"),
    @NamedQuery(name = "Voucher.findByVoucherpref", query = "SELECT v FROM Voucher v WHERE v.voucherpref = :voucherpref"),
    @NamedQuery(name = "Voucher.findByVoucherdigit", query = "SELECT v FROM Voucher v WHERE v.voucherdigit = :voucherdigit"),
    @NamedQuery(name = "Voucher.findByVoucherstartdate", query = "SELECT v FROM Voucher v WHERE v.voucherstartdate = :voucherstartdate"),
    @NamedQuery(name = "Voucher.findByVoucherenddate", query = "SELECT v FROM Voucher v WHERE v.voucherenddate = :voucherenddate"),
    @NamedQuery(name = "Voucher.findByVouchertype", query = "SELECT v FROM Voucher v WHERE v.vouchertype = :vouchertype"),
    @NamedQuery(name = "Voucher.findByVouchervalue", query = "SELECT v FROM Voucher v WHERE v.vouchervalue = :vouchervalue"),
    @NamedQuery(name = "Voucher.findByStatus", query = "SELECT v FROM Voucher v WHERE v.status = :status"),
    @NamedQuery(name = "Voucher.findByCreateddate", query = "SELECT v FROM Voucher v WHERE v.createddate = :createddate"),
    @NamedQuery(name = "Voucher.findByCreatedby", query = "SELECT v FROM Voucher v WHERE v.createdby = :createdby"),
    @NamedQuery(name = "Voucher.findByUpdateddate", query = "SELECT v FROM Voucher v WHERE v.updateddate = :updateddate"),
    @NamedQuery(name = "Voucher.findByUpdatedby", query = "SELECT v FROM Voucher v WHERE v.updatedby = :updatedby")})
public class Voucher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "voucherid")
    private Integer voucherid;
    @Basic(optional = false)
    @Column(name = "voucherdesc")
    private String voucherdesc;
    @Basic(optional = false)
    @Column(name = "voucherpref")
    private String voucherpref;
    @Basic(optional = false)
    @Column(name = "voucherdigit")
    private int voucherdigit;
    @Basic(optional = false)
    @Column(name = "voucherstartdate")
    @Temporal(TemporalType.DATE)
    private Date voucherstartdate;
    @Basic(optional = false)
    @Column(name = "voucherenddate")
    @Temporal(TemporalType.DATE)
    private Date voucherenddate;
    @Column(name = "vouchertype")
    private Integer vouchertype;
    @Basic(optional = false)
    @Column(name = "vouchervalue")
    private double vouchervalue;
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

    public Voucher() {
    }

    public Voucher(Integer voucherid) {
        this.voucherid = voucherid;
    }

    public Voucher(Integer voucherid, String voucherdesc, String voucherpref, int voucherdigit, Date voucherstartdate, Date voucherenddate, double vouchervalue, boolean status, Date createddate, String createdby) {
        this.voucherid = voucherid;
        this.voucherdesc = voucherdesc;
        this.voucherpref = voucherpref;
        this.voucherdigit = voucherdigit;
        this.voucherstartdate = voucherstartdate;
        this.voucherenddate = voucherenddate;
        this.vouchervalue = vouchervalue;
        this.status = status;
        this.createddate = createddate;
        this.createdby = createdby;
    }

    public Integer getVoucherid() {
        return voucherid;
    }

    public void setVoucherid(Integer voucherid) {
        this.voucherid = voucherid;
    }

    public String getVoucherdesc() {
        return voucherdesc;
    }

    public void setVoucherdesc(String voucherdesc) {
        this.voucherdesc = voucherdesc;
    }

    public String getVoucherpref() {
        return voucherpref;
    }

    public void setVoucherpref(String voucherpref) {
        this.voucherpref = voucherpref;
    }

    public int getVoucherdigit() {
        return voucherdigit;
    }

    public void setVoucherdigit(int voucherdigit) {
        this.voucherdigit = voucherdigit;
    }

    public Date getVoucherstartdate() {
        return voucherstartdate;
    }

    public void setVoucherstartdate(Date voucherstartdate) {
        this.voucherstartdate = voucherstartdate;
    }

    public Date getVoucherenddate() {
        return voucherenddate;
    }

    public void setVoucherenddate(Date voucherenddate) {
        this.voucherenddate = voucherenddate;
    }

    public Integer getVouchertype() {
        return vouchertype;
    }

    public void setVouchertype(Integer vouchertype) {
        this.vouchertype = vouchertype;
    }

    public double getVouchervalue() {
        return vouchervalue;
    }

    public void setVouchervalue(double vouchervalue) {
        this.vouchervalue = vouchervalue;
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
        hash += (voucherid != null ? voucherid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voucher)) {
            return false;
        }
        Voucher other = (Voucher) object;
        if ((this.voucherid == null && other.voucherid != null) || (this.voucherid != null && !this.voucherid.equals(other.voucherid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Voucher[ voucherid=" + voucherid + " ]";
    }
    
}
