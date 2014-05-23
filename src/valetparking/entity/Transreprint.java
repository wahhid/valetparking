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
@Table(name = "transreprint", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transreprint.findAll", query = "SELECT t FROM Transreprint t"),
    @NamedQuery(name = "Transreprint.findById", query = "SELECT t FROM Transreprint t WHERE t.id = :id"),
    @NamedQuery(name = "Transreprint.findByTransid", query = "SELECT t FROM Transreprint t WHERE t.transid = :transid"),
    @NamedQuery(name = "Transreprint.findByOpr", query = "SELECT t FROM Transreprint t WHERE t.opr = :opr"),
    @NamedQuery(name = "Transreprint.findByReprintdate", query = "SELECT t FROM Transreprint t WHERE t.reprintdate = :reprintdate")})
public class Transreprint implements Serializable {
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
    @Column(name = "opr")
    private String opr;
    @Basic(optional = false)
    @Column(name = "reprintdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reprintdate;

    public Transreprint() {
    }

    public Transreprint(Integer id) {
        this.id = id;
    }

    public Transreprint(Integer id, String transid, String opr, Date reprintdate) {
        this.id = id;
        this.transid = transid;
        this.opr = opr;
        this.reprintdate = reprintdate;
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

    public String getOpr() {
        return opr;
    }

    public void setOpr(String opr) {
        this.opr = opr;
    }

    public Date getReprintdate() {
        return reprintdate;
    }

    public void setReprintdate(Date reprintdate) {
        this.reprintdate = reprintdate;
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
        if (!(object instanceof Transreprint)) {
            return false;
        }
        Transreprint other = (Transreprint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Transreprint[ id=" + id + " ]";
    }
    
}
