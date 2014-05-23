/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "configuration", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuration.findAll", query = "SELECT c FROM Configuration c"),
    @NamedQuery(name = "Configuration.findByCompanyid", query = "SELECT c FROM Configuration c WHERE c.companyid = :companyid"),
    @NamedQuery(name = "Configuration.findByCompanyname", query = "SELECT c FROM Configuration c WHERE c.companyname = :companyname"),
    @NamedQuery(name = "Configuration.findByAddress", query = "SELECT c FROM Configuration c WHERE c.address = :address"),
    @NamedQuery(name = "Configuration.findByCity", query = "SELECT c FROM Configuration c WHERE c.city = :city"),
    @NamedQuery(name = "Configuration.findByProvince", query = "SELECT c FROM Configuration c WHERE c.province = :province"),
    @NamedQuery(name = "Configuration.findByCountry", query = "SELECT c FROM Configuration c WHERE c.country = :country"),
    @NamedQuery(name = "Configuration.findByZipcode", query = "SELECT c FROM Configuration c WHERE c.zipcode = :zipcode"),
    @NamedQuery(name = "Configuration.findByReportpath", query = "SELECT c FROM Configuration c WHERE c.reportpath = :reportpath")})
public class Configuration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "companyid")
    private String companyid;
    @Column(name = "companyname")
    private String companyname;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "province")
    private String province;
    @Column(name = "country")
    private String country;
    @Column(name = "zipcode")
    private String zipcode;
    @Basic(optional = false)
    @Column(name = "reportpath")
    private String reportpath;

    public Configuration() {
    }

    public Configuration(String companyid) {
        this.companyid = companyid;
    }

    public Configuration(String companyid, String reportpath) {
        this.companyid = companyid;
        this.reportpath = reportpath;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getReportpath() {
        return reportpath;
    }

    public void setReportpath(String reportpath) {
        this.reportpath = reportpath;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyid != null ? companyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuration)) {
            return false;
        }
        Configuration other = (Configuration) object;
        if ((this.companyid == null && other.companyid != null) || (this.companyid != null && !this.companyid.equals(other.companyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Configuration[ companyid=" + companyid + " ]";
    }
    
}
