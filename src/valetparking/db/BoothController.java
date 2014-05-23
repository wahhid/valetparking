/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.db;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import valetparking.ErrHandling;
import valetparking.entity.Booth;
import valetparking.PersistenceConnection;

/**
 *
 * @author wahhid
 */
public class BoothController extends PersistenceConnection {    
    
    
    private ErrHandling errHandling = new ErrHandling();
    
    public BoothController(){
        this.errHandling.setClassName("BoothController");
        
    }
        
    public List<Booth> getAll(){       
        this.errHandling.setMethodName("getAll");
        List<Booth> os = new ArrayList();        
        EntityManager em = this.getEntityManager();       
        try{
            Query query = em.createNamedQuery("Booth.findAll");        
            query.setHint("eclipselink.refresh", "true");
            os = query.getResultList();                
        }catch(NoResultException ex){
            os = null;
            this.errHandling.setErrStatus(true);            
            this.errHandling.setErrMsg(ex.getMessage());
        }finally{
            em.close();
        }
        return os;
    }
    
    public Booth getByBoothID(String boothid){
        this.errHandling.setMethodName("getByBoothID");
        Booth booth;
        EntityManager em = this.getEntityManager();        
        try{
            Query query = em.createNamedQuery("Booth.findByBoothid").setParameter("boothid", boothid);
            query.setHint("eclipselink.refresh", "true");
            booth = (Booth) query.getSingleResult();            
        }catch(NoResultException ex){
            booth = null;                        
            this.errHandling.setErrStatus(true);            
            this.errHandling.setErrMsg(ex.getMessage());            
        }finally{
            em.close();
        }     
        return booth;
    }

    public List<Booth> findByQuery(String strSQL){
        List<Booth> os = null;
        EntityManager em = this.getEntityManager();
        try{
            Query query = em.createQuery(strSQL);
            query.setHint("eclipselink.refresh", "true");
            os = query.getResultList();
        }catch(NoResultException ex){
            System.out.println(ex.getMessage());        
            os = null;
            this.errHandling.setErrStatus(true);            
            this.errHandling.setErrMsg(ex.getMessage());            
        }finally{
            em.close();
        }                                
        return os;
    }
                     
    public ErrHandling getErrHandling() {
        return errHandling;
    }
    
    
    
}
