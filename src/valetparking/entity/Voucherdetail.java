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
 * @author wahhid
 */
@Entity
@Table(name = "voucherdetail", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voucherdetail.findAll", query = "SELECT v FROM Voucherdetail v"),
    @NamedQuery(name = "Voucherdetail.findByVouchdetailid", query = "SELECT v FROM Voucherdetail v WHERE v.vouchdetailid = :vouchdetailid"),
    @NamedQuery(name = "Voucherdetail.findByVoucherid", query = "SELECT v FROM Voucherdetail v WHERE v.voucherid = :voucherid"),
    @NamedQuery(name = "Voucherdetail.findByVoucherusedate", query = "SELECT v FROM Voucherdetail v WHERE v.voucherusedate = :voucherusedate"),
    @NamedQuery(name = "Voucherdetail.findByStatus", query = "SELECT v FROM Voucherdetail v WHERE v.status = :status"),
    @NamedQuery(name = "Voucherdetail.findByCreateddate", query = "SELECT v FROM Voucherdetail v WHERE v.createddate = :createddate"),
    @NamedQuery(name = "Voucherdetail.findByCreatedby", query = "SELECT v FROM Voucherdetail v WHERE v.createdby = :createdby"),
    @NamedQuery(name = "Voucherdetail.findByUpdateddate", query = "SELECT v FROM Voucherdetail v WHERE v.updateddate = :updateddate"),
    @NamedQuery(name = "Voucherdetail.findByUpdatedby", query = "SELECT v FROM Voucherdetail v WHERE v.updatedby = :updatedby")})
public class Voucherdetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "vouchdetailid")
    private String vouchdetailid;
    @Basic(optional = false)
    @Column(name = "voucherid")
    private int voucherid;
    @Column(name = "voucherusedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date voucherusedate;
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

    public Voucherdetail() {
    }

    public Voucherdetail(String vouchdetailid) {
        this.vouchdetailid = vouchdetailid;
    }

    public Voucherdetail(String vouchdetailid, int voucherid, boolean status, Date createddate, String createdby) {
        this.vouchdetailid = vouchdetailid;
        this.voucherid = voucherid;
        this.status = status;
        this.createddate = createddate;
        this.createdby = createdby;
    }

    public String getVouchdetailid() {
        return vouchdetailid;
    }

    public void setVouchdetailid(String vouchdetailid) {
        this.vouchdetailid = vouchdetailid;
    }

    public int getVoucherid() {
        return voucherid;
    }

    public void setVoucherid(int voucherid) {
        this.voucherid = voucherid;
    }

    public Date getVoucherusedate() {
        return voucherusedate;
    }

    public void setVoucherusedate(Date voucherusedate) {
        this.voucherusedate = voucherusedate;
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
        hash += (vouchdetailid != null ? vouchdetailid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voucherdetail)) {
            return false;
        }
        Voucherdetail other = (Voucherdetail) object;
        if ((this.vouchdetailid == null && other.vouchdetailid != null) || (this.vouchdetailid != null && !this.vouchdetailid.equals(other.vouchdetailid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Voucherdetail[ vouchdetailid=" + vouchdetailid + " ]";
    }
    
}
