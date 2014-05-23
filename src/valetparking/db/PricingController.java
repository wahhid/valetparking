    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.db;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import valetparking.entity.Pricing;
import valetparking.PersistenceConnection;

/**
 *
 * @author wahhid
 */
public class PricingController extends PersistenceConnection {
             
    public PricingController(){
        
    }
    
    public Pricing getActivePricing(){
        Pricing pricing=null;
        EntityManager em = this.getEntityManager();
        try{            
            Query query = em.createNamedQuery("Pricing.findByPricingenable").setParameter("pricingenable", true);
            query.setHint("eclipselink.refresh", "true");                    
            pricing = (Pricing) query.getSingleResult();            
        }catch(NoResultException ex){
            pricing = null;            
        }finally{
            em.close();
        }
        return pricing;
    }
        
}
