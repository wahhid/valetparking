/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wahhid
 */
@Entity
@Table(name = "idseq", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Idseq.findAll", query = "SELECT i FROM Idseq i"),
    @NamedQuery(name = "Idseq.findBySeqid", query = "SELECT i FROM Idseq i WHERE i.seqid = :seqid"),
    @NamedQuery(name = "Idseq.findByBoothid", query = "SELECT i FROM Idseq i WHERE i.boothid = :boothid"),
    @NamedQuery(name = "Idseq.findBySeqvalue", query = "SELECT i FROM Idseq i WHERE i.seqvalue = :seqvalue")})
public class Idseq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "seqid")
    private Integer seqid;
    @Basic(optional = false)
    @Column(name = "boothid")
    private String boothid;
    @Basic(optional = false)
    @Column(name = "seqvalue")
    private int seqvalue;

    public Idseq() {
    }

    public Idseq(Integer seqid) {
        this.seqid = seqid;
    }

    public Idseq(Integer seqid, String boothid, int seqvalue) {
        this.seqid = seqid;
        this.boothid = boothid;
        this.seqvalue = seqvalue;
    }

    public Integer getSeqid() {
        return seqid;
    }

    public void setSeqid(Integer seqid) {
        this.seqid = seqid;
    }

    public String getBoothid() {
        return boothid;
    }

    public void setBoothid(String boothid) {
        this.boothid = boothid;
    }

    public int getSeqvalue() {
        return seqvalue;
    }

    public void setSeqvalue(int seqvalue) {
        this.seqvalue = seqvalue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seqid != null ? seqid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idseq)) {
            return false;
        }
        Idseq other = (Idseq) object;
        if ((this.seqid == null && other.seqid != null) || (this.seqid != null && !this.seqid.equals(other.seqid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Idseq[ seqid=" + seqid + " ]";
    }
    
}
