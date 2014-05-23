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
import javax.persistence.Lob;
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
@Table(name = "transcorrection", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transcorrection.findAll", query = "SELECT t FROM Transcorrection t"),
    @NamedQuery(name = "Transcorrection.findById", query = "SELECT t FROM Transcorrection t WHERE t.id = :id"),
    @NamedQuery(name = "Transcorrection.findByTransid", query = "SELECT t FROM Transcorrection t WHERE t.transid = :transid"),
    @NamedQuery(name = "Transcorrection.findByCreateddate", query = "SELECT t FROM Transcorrection t WHERE t.createddate = :createddate"),
    @NamedQuery(name = "Transcorrection.findByCreatedby", query = "SELECT t FROM Transcorrection t WHERE t.createdby = :createdby")})
public class Transcorrection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "transid")
    private String transid;
    @Basic(optional = false)
    @Lob
    @Column(name = "remark")
    private String remark;
    @Basic(optional = false)
    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Basic(optional = false)
    @Column(name = "createdby")
    private String createdby;

    public Transcorrection() {
    }

    public Transcorrection(Integer id) {
        this.id = id;
    }

    public Transcorrection(Integer id, String transid, String remark, Date createddate, String createdby) {
        this.id = id;
        this.transid = transid;
        this.remark = remark;
        this.createddate = createddate;
        this.createdby = createdby;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transcorrection)) {
            return false;
        }
        Transcorrection other = (Transcorrection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Transcorrection[ id=" + id + " ]";
    }
    
}
