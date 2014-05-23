/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.db;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import valetparking.entity.Transvalet;
import valetparking.PersistenceConnection;

/**
 *
 * @author wahhid
 */
public class TransvaletController extends PersistenceConnection{
    
    public TransvaletController(){
        
    }
    
    
    public Transvalet getByTransID(String transid){
        Transvalet transvalet;
        EntityManager em = this.getEntityManager();
        try{
            Query query = em.createNamedQuery("Transvalet.findByTransid").setParameter("transid", transid);
            query.setHint("eclipselink.refresh", "true");
            transvalet = (Transvalet) query.getSingleResult();
        }catch(NoResultException ex){            
            transvalet = null;
        }finally{
            em.close();
        }
        return transvalet;
    }
    
    public void add(Transvalet transvalet){
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{                        
            em.persist(transvalet);
            em.getTransaction().commit();            
        }catch(Exception ex){            
            transvalet = null;            
            em.getTransaction().rollback();
        }finally{
            em.close();
        }                
    }
    
    public void update(Transvalet transvalet){
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{                       
            em.merge(transvalet);
            em.getTransaction().commit();            
        }catch(Exception ex){
            transvalet = null;            
            em.getTransaction().rollback();            
        }finally{
            em.close();
        }        
    }
    
    public Transvalet isCarExist(String carnumber){
        Transvalet transvalet;
        EntityManager em = this.getEntityManager();
        try{          
            String strSQL = "SELECT t from Transvalet t WHERE t.carnumber = :carnumber AND t.status = :status";
            Query query = em.createQuery(strSQL).setParameter("carnumber", carnumber.toUpperCase().trim()).setParameter("status", 0); 
            query.setHint("eclipselink.refresh", "true");
            transvalet = (Transvalet) query.getSingleResult();                               
        }catch(NoResultException ex){
            transvalet = null;                       
        }finally{
            em.close();
        }
        return transvalet;
    }        
}
