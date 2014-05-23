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
@Table(name = "vouchertype", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vouchertype.findAll", query = "SELECT v FROM Vouchertype v"),
    @NamedQuery(name = "Vouchertype.findByVouchertypeid", query = "SELECT v FROM Vouchertype v WHERE v.vouchertypeid = :vouchertypeid"),
    @NamedQuery(name = "Vouchertype.findByVouchertypename", query = "SELECT v FROM Vouchertype v WHERE v.vouchertypename = :vouchertypename")})
public class Vouchertype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vouchertypeid")
    private Integer vouchertypeid;
    @Basic(optional = false)
    @Column(name = "vouchertypename")
    private String vouchertypename;

    public Vouchertype() {
    }

    public Vouchertype(Integer vouchertypeid) {
        this.vouchertypeid = vouchertypeid;
    }

    public Vouchertype(Integer vouchertypeid, String vouchertypename) {
        this.vouchertypeid = vouchertypeid;
        this.vouchertypename = vouchertypename;
    }

    public Integer getVouchertypeid() {
        return vouchertypeid;
    }

    public void setVouchertypeid(Integer vouchertypeid) {
        this.vouchertypeid = vouchertypeid;
    }

    public String getVouchertypename() {
        return vouchertypename;
    }

    public void setVouchertypename(String vouchertypename) {
        this.vouchertypename = vouchertypename;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vouchertypeid != null ? vouchertypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vouchertype)) {
            return false;
        }
        Vouchertype other = (Vouchertype) object;
        if ((this.vouchertypeid == null && other.vouchertypeid != null) || (this.vouchertypeid != null && !this.vouchertypeid.equals(other.vouchertypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Vouchertype[ vouchertypeid=" + vouchertypeid + " ]";
    }
    
}
