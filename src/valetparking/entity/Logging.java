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
@Table(name = "logging", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logging.findAll", query = "SELECT l FROM Logging l"),
    @NamedQuery(name = "Logging.findByLogid", query = "SELECT l FROM Logging l WHERE l.logid = :logid"),
    @NamedQuery(name = "Logging.findByLogdatetime", query = "SELECT l FROM Logging l WHERE l.logdatetime = :logdatetime"),
    @NamedQuery(name = "Logging.findByLogcategory", query = "SELECT l FROM Logging l WHERE l.logcategory = :logcategory"),
    @NamedQuery(name = "Logging.findByLogemployee", query = "SELECT l FROM Logging l WHERE l.logemployee = :logemployee")})
public class Logging implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "logid")
    private String logid;
    @Basic(optional = false)
    @Column(name = "logdatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logdatetime;
    @Basic(optional = false)
    @Column(name = "logcategory")
    private int logcategory;
    @Basic(optional = false)
    @Lob
    @Column(name = "logdesc")
    private String logdesc;
    @Basic(optional = false)
    @Column(name = "logemployee")
    private String logemployee;

    public Logging() {
    }

    public Logging(String logid) {
        this.logid = logid;
    }

    public Logging(String logid, Date logdatetime, int logcategory, String logdesc, String logemployee) {
        this.logid = logid;
        this.logdatetime = logdatetime;
        this.logcategory = logcategory;
        this.logdesc = logdesc;
        this.logemployee = logemployee;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public Date getLogdatetime() {
        return logdatetime;
    }

    public void setLogdatetime(Date logdatetime) {
        this.logdatetime = logdatetime;
    }

    public int getLogcategory() {
        return logcategory;
    }

    public void setLogcategory(int logcategory) {
        this.logcategory = logcategory;
    }

    public String getLogdesc() {
        return logdesc;
    }

    public void setLogdesc(String logdesc) {
        this.logdesc = logdesc;
    }

    public String getLogemployee() {
        return logemployee;
    }

    public void setLogemployee(String logemployee) {
        this.logemployee = logemployee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logid != null ? logid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logging)) {
            return false;
        }
        Logging other = (Logging) object;
        if ((this.logid == null && other.logid != null) || (this.logid != null && !this.logid.equals(other.logid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Logging[ logid=" + logid + " ]";
    }
    
}
