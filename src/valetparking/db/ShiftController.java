/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import valetparking.entity.Shift;
import valetparking.PersistenceConnection;

/**
 *
 * @author wahhid
 */
public class ShiftController extends PersistenceConnection {
    
    public ShiftController(){        
    }
    
    public List<Shift> getAll(){
        List<Shift> shifts;
        EntityManager em = this.getEntityManager();        
        try{            
            Query query = em.createNamedQuery("Shift.findAll");
            query.setHint("eclipselink.refresh", "true");
            shifts = query.getResultList();                        
        }catch(NoResultException ex){
            shifts = null;            
        }finally{
            em.close();
        }
        return shifts;
    }
}
