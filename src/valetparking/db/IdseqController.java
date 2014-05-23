/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import valetparking.entity.Idseq;
import valetparking.PersistenceConnection;

/**
 *
 * @author wahhid
 */
public class IdseqController extends PersistenceConnection {
    
    
    public IdseqController(){
        
    }
    
    public Idseq getByBoothID(String boothid){
        Idseq idseq;
        EntityManager em = this.getEntityManager();
        try{           
            Query query = em.createNamedQuery("Idseq.findByBoothid").setParameter("boothid", boothid);
            query.setHint("eclipselink.refresh", "true");
            idseq = (Idseq) query.getSingleResult();
        }catch(NoResultException ex){
            idseq = null;            
        }finally{
            em.close();
        }
        return idseq;
    }
    
    public void update(Idseq idseq){    
        EntityManager em = this.getEntityManager();
        try{            
            em.getTransaction().begin();
            em.merge(idseq);            
            em.getTransaction().commit();
        }catch(Exception ex){
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }
}
