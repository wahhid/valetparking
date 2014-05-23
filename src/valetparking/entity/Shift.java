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
@Table(name = "shift", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s"),
    @NamedQuery(name = "Shift.findByShiftid", query = "SELECT s FROM Shift s WHERE s.shiftid = :shiftid"),
    @NamedQuery(name = "Shift.findByShiftname", query = "SELECT s FROM Shift s WHERE s.shiftname = :shiftname"),
    @NamedQuery(name = "Shift.findByStarttime", query = "SELECT s FROM Shift s WHERE s.starttime = :starttime"),
    @NamedQuery(name = "Shift.findByEndtime", query = "SELECT s FROM Shift s WHERE s.endtime = :endtime"),
    @NamedQuery(name = "Shift.findByNextday", query = "SELECT s FROM Shift s WHERE s.nextday = :nextday")})
public class Shift implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "shiftid")
    private String shiftid;
    @Basic(optional = false)
    @Column(name = "shiftname")
    private String shiftname;
    @Basic(optional = false)
    @Column(name = "starttime")
    @Temporal(TemporalType.TIME)
    private Date starttime;
    @Basic(optional = false)
    @Column(name = "endtime")
    @Temporal(TemporalType.TIME)
    private Date endtime;
    @Basic(optional = false)
    @Column(name = "nextday")
    private boolean nextday;

    public Shift() {
    }

    public Shift(String shiftid) {
        this.shiftid = shiftid;
    }

    public Shift(String shiftid, String shiftname, Date starttime, Date endtime, boolean nextday) {
        this.shiftid = shiftid;
        this.shiftname = shiftname;
        this.starttime = starttime;
        this.endtime = endtime;
        this.nextday = nextday;
    }

    public String getShiftid() {
        return shiftid;
    }

    public void setShiftid(String shiftid) {
        this.shiftid = shiftid;
    }

    public String getShiftname() {
        return shiftname;
    }

    public void setShiftname(String shiftname) {
        this.shiftname = shiftname;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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
        hash += (shiftid != null ? shiftid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shift)) {
            return false;
        }
        Shift other = (Shift) object;
        if ((this.shiftid == null && other.shiftid != null) || (this.shiftid != null && !this.shiftid.equals(other.shiftid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Shift[ shiftid=" + shiftid + " ]";
    }
    
}
