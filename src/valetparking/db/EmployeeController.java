/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.db;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import valetparking.entity.Employee;
import valetparking.PersistenceConnection;

/**
 *
 * @author wahhid
 */
public class EmployeeController extends PersistenceConnection {
                
    public EmployeeController(){
        
    }
    
    public List<Employee> getAll(){        
        EntityManager em = this.getEntityManager();
        Query query = em.createNamedQuery("Employee.findAll");                
        return query.getResultList();
    }

    public Employee getByNik(String nik){
        Employee employee = null;
        EntityManager em = this.getEntityManager();
        try{            
            Query query = em.createNamedQuery("Employee.findByNik").setParameter("nik", nik);
            query.setHint("eclipselink.refresh", "true");
            employee = (Employee) query.getSingleResult();            
        }catch(NoResultException ex){
            employee = null;
        }finally{
            em.close();
        }
        return employee;
    }



        
    
}
