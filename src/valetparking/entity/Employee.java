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
 * @author root
 */
@Entity
@Table(name = "employee", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByNik", query = "SELECT e FROM Employee e WHERE e.nik = :nik"),
    @NamedQuery(name = "Employee.findByFullname", query = "SELECT e FROM Employee e WHERE e.fullname = :fullname"),
    @NamedQuery(name = "Employee.findByInitial", query = "SELECT e FROM Employee e WHERE e.initial = :initial"),
    @NamedQuery(name = "Employee.findByPassword", query = "SELECT e FROM Employee e WHERE e.password = :password"),
    @NamedQuery(name = "Employee.findByEmployeetype", query = "SELECT e FROM Employee e WHERE e.employeetype = :employeetype"),
    @NamedQuery(name = "Employee.findByDriver", query = "SELECT e FROM Employee e WHERE e.driver = :driver"),
    @NamedQuery(name = "Employee.findByStatus", query = "SELECT e FROM Employee e WHERE e.status = :status"),
    @NamedQuery(name = "Employee.findByCreateddate", query = "SELECT e FROM Employee e WHERE e.createddate = :createddate"),
    @NamedQuery(name = "Employee.findByCreatedby", query = "SELECT e FROM Employee e WHERE e.createdby = :createdby"),
    @NamedQuery(name = "Employee.findByUpdateddate", query = "SELECT e FROM Employee e WHERE e.updateddate = :updateddate"),
    @NamedQuery(name = "Employee.findByUpdatedby", query = "SELECT e FROM Employee e WHERE e.updatedby = :updatedby")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nik")
    private String nik;
    @Basic(optional = false)
    @Column(name = "fullname")
    private String fullname;
    @Basic(optional = false)
    @Column(name = "initial")
    private String initial;
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "employeetype")
    private String employeetype;
    @Basic(optional = false)
    @Column(name = "driver")
    private boolean driver;
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

    public Employee() {
    }

    public Employee(String nik) {
        this.nik = nik;
    }

    public Employee(String nik, String fullname, String initial, String employeetype, boolean driver, boolean status, Date createddate, String createdby) {
        this.nik = nik;
        this.fullname = fullname;
        this.initial = initial;
        this.employeetype = employeetype;
        this.driver = driver;
        this.status = status;
        this.createddate = createddate;
        this.createdby = createdby;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(String employeetype) {
        this.employeetype = employeetype;
    }

    public boolean getDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
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
        hash += (nik != null ? nik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.nik == null && other.nik != null) || (this.nik != null && !this.nik.equals(other.nik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valetparking.entity.Employee[ nik=" + nik + " ]";
    }
    
}
