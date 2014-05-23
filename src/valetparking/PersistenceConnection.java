/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wahhid
 */
public class PersistenceConnection{
    
    protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("ValetParkingPU");  
            
    public PersistenceConnection(){
        
    }
    
    protected EntityManager getEntityManager(){
        return this.emf.createEntityManager();
    }
    
    
}
