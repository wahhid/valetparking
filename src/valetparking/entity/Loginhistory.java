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
@Table(name = "loginhistory", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Loginhistory.findAll", query = "SELECT l FROM Loginhistory l"),
    @NamedQuery(name = "Loginhistory.findByLogid", query = "SELECT l FROM Loginhistory l WHERE l.logid = :logid"),
    @NamedQuery(name = "Loginhistory.findByNik", query = "SELECT l FROM Loginhistory l WHERE l.nik = :nik"),
    @NamedQuery(name = "Loginhistory.findByLogindatetime", query = "SELECT l FROM Loginhistory l WHERE l.logindatetime = :logindatetime"),
    @NamedQuery(name = "Loginhistory.findByLogoutdatetime", query = "SELECT l FROM Loginhistory l WHERE l.logoutdatetime = :logoutdatetime")})
public class Loginhistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "logid")
    private Integer logid;
    @Basic(optional = false)
    @Column(name = "nik")
    private String nik;
    @Column(name = "logindatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logindatetime;
    @Column(name = "logoutdatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutdatetime;

    public Loginhistory() {
    }

    public Loginhistory(Integer logid) {
        this.logid = logid;
    }

    public Loginhistory(Integer logid, String nik) {
        this.logid = logid;
        this.nik = nik;
    }

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Date getLogindatetime() {
        return logindatetime;
    }

    public void setLogindatetime(Date logindatetime) {
        this.logindatetime = logindatetime;
    }

    public Date getLogoutdatetime() {
        return logoutdatetime;
    }

    public void setLogoutdatetime(Date logoutdatetime) {
        this.logoutdatetime = logoutdatetime;
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
        if (!(object instanceof Loginhistory)) {
            return false;
        }
        Loginhistory other = (Loginhistory) object;
        if ((this.logid == null && other.logid != null) || (this.logid != null && !this.logid.equals(other.logid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Loginhistory[ logid=" + logid + " ]";
    }
    
}
